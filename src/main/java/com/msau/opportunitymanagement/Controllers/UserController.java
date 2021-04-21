package com.msau.opportunitymanagement.Controllers;


import com.msau.opportunitymanagement.DAO.UserDao;
import com.msau.opportunitymanagement.Models.Opportunity;
import com.msau.opportunitymanagement.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserDao userDao;

    @PostMapping(path="/login")
    @ResponseBody
    public int login(@RequestBody User user, @RequestHeader(value="Authorization")String token){
        System.out.println("in login   "+user+ " "  + token);
        token = token.substring(20,30);
        System.out.println(token);
        user.setToken(token);
        if(userDao.findUser(user.getEmail())!=null)
        {
            System.out.println("helllo");
            return userDao.updateUser(user,token);
        }
        else {

            return userDao.insertUser(user, token);
        }
    }

}
