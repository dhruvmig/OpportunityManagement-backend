package com.msau.opportunitymanagement.RowMapper;

import com.msau.opportunitymanagement.Models.User;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setId(rs.getString("id"));
        user.setToken(rs.getString("token"));
        user.setUser_id(rs.getString("user_id"));
        return user;
    }

}
