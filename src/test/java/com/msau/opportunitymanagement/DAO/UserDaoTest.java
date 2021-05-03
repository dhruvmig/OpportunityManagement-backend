package com.msau.opportunitymanagement.DAO;


import com.msau.opportunitymanagement.Models.User;
import com.msau.opportunitymanagement.RowMapper.UserRowMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserDaoTest {
    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    UserDaoImpl userDao;


    @Test
    public void shouldFindUserByEmail() {
        User tomatch = new User("name","email","1000401","ldjslfk",null);
        Mockito.when(jdbcTemplate.queryForObject(
                Mockito.anyString(),
                Mockito.any(UserRowMapper.class)
        )).thenReturn(tomatch);
        User result = userDao.findUser("email");
        Assert.assertEquals(result, tomatch);
    }

    @Test
    public void shouldGetUserNameById(){
        User tomatch = new User("name","email","1000401","ldjslfk",null);
        Mockito.when(jdbcTemplate.queryForObject(
                Mockito.anyString(),
                Mockito.any(UserRowMapper.class)
        )).thenReturn(tomatch);
        User result = userDao.findUser("1000401");
        Assert.assertEquals(result, tomatch);
    }

//    @Test
//    public void shouldInsertUser(){
//        int tomatch = 8;
////        User tomatch = new User();
//        Mockito.when(jdbcTemplate.update(
//                Mockito.anyString(),
//                Mockito.any(UserRowMapper.class)
//        )).thenReturn(tomatch);
//        User result = userDao.insertUser(new User(), "");
//        Assert.assertEquals(result, new User());
//    }
}
