package com.msau.opportunitymanagement.DAO;

import com.msau.opportunitymanagement.Models.User;

public interface UserDao {
    public User findUser(String user);

    public int insertUser(User u , String token);

    public int updateUser(User u,String token);

}
