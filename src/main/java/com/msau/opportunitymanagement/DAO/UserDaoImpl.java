package com.msau.opportunitymanagement.DAO;

import com.msau.opportunitymanagement.Models.User;
import com.msau.opportunitymanagement.RowMapper.OpportunityRowMapper;
import com.msau.opportunitymanagement.RowMapper.UserRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl implements  UserDao{

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private JdbcTemplate jdbcTemplate;
    public UserDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findUser(String user) {
        logger.info("inside Find User DAO");
        try{
            User x=jdbcTemplate.queryForObject("SELECT * FROM USER WHERE email='"+user+"';", new UserRowMapper());
            return x;
        }
        catch(Exception e)
        {
            logger.error("Error to find user"+e);
            return null;
        }
    }
    @Override
    public User insertUser(User u, String token) {
        logger.info("inside insert user");
        String insertSql = "INSERT INTO user (name,email, id,user_id,token) VALUES (?,?,?,?,?)";
        int a =  jdbcTemplate.update(insertSql, new Object[]{u.getName(),u.getEmail(),u.getId(),u.getUser_id(),u.getToken()});
        return u;
    }

    @Override
    public User updateUser(User u, String token) {
        logger.info("inside update user");
        System.out.println("user here isi "+u);
        String sql = "UPDATE user SET  token=? where email=?";
        int x= jdbcTemplate.update(sql,new Object[]{u.getToken(),u.getEmail()});
        return u;
    }

    @Override
    public String getUserName(String id) {
        logger.info("inside get user name"+id);
        String sql = "SELECT * from user where id="+id+";";
        try{
            User x = jdbcTemplate.queryForObject(sql,new UserRowMapper());
            System.out.println("user name is "+x);
            return x.getName();
        }
        catch (Exception e)
        {
            return null;
        }

    }
}
