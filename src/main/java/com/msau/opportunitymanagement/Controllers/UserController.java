package com.msau.opportunitymanagement.Controllers;


import com.msau.opportunitymanagement.DAO.UserDao;
import com.msau.opportunitymanagement.Models.Opportunity;
import com.msau.opportunitymanagement.Models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserDao userDao;

    @PostMapping(path="/login")
    @ResponseBody
    public User login(@RequestBody User user, @RequestHeader(value="Authorization")String token){
        logger.info("Inside login user controller");
        token = token.substring(20,30);
        System.out.println(token);
        user.setToken(token);
        if(userDao.findUser(user.getEmail())!=null)
        {
            logger.info("User record exists");
            return userDao.updateUser(user,token);
        }
        else {
            logger.info("Adding this new user");
            return userDao.insertUser(user, token);
        }
    }

    @ResponseBody
    public String getUserName(@RequestBody String id)
    {
        logger.info("Inside get username controller");
        return userDao.getUserName(id);
    }

}
