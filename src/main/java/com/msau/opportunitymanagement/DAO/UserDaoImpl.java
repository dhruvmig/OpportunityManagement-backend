package com.msau.opportunitymanagement.DAO;

import com.msau.opportunitymanagement.Models.User;
import com.msau.opportunitymanagement.RowMapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl implements  UserDao{
    private JdbcTemplate jdbcTemplate;
    public UserDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findUser(String user) {

        try{
            User x=jdbcTemplate.queryForObject("SELECT * FROM USER WHERE email='"+user+"';", new UserRowMapper());
            return x;
        }
        catch(Exception e)
        {
            return null;
        }
    }
    @Override
    public User insertUser(User u, String token) {
        String insertSql = "INSERT INTO user (name,email, id,user_id,token) VALUES (?,?,?,?,?)";
        int a =  jdbcTemplate.update(insertSql, new Object[]{u.getName(),u.getEmail(),u.getId(),u.getUser_id(),u.getToken()});
        return u;
    }

    @Override
    public User updateUser(User u, String token) {

        String sql = "UPDATE user SET  token=? where email=?";
        int x= jdbcTemplate.update(sql,new Object[]{u.getToken(),u.getEmail()});
        return u;
    }
}
