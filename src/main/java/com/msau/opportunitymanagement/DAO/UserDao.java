package com.msau.opportunitymanagement.DAO;

import com.msau.opportunitymanagement.Models.User;

public interface UserDao {
    public User findUser(String user);

    public User insertUser(User u , String token);

    public User updateUser(User u,String token);

}
