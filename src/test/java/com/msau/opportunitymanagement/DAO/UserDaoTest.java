package com.msau.opportunitymanagement.DAO;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.msau.opportunitymanagement.Exceptions.NoRecordFound;
import com.msau.opportunitymanagement.Models.User;
import com.msau.opportunitymanagement.RowMapper.UserRowMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserDaoTest {

    @Autowired
    public MockMvc mockMvc;

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    UserDaoImpl userDao;

    @Autowired
    private ObjectMapper objectMapper;


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
        String result = userDao.getUserName("1000401");
        Assert.assertEquals(result, "name");
    }

//    @Test
//    public void shouldInsertUser(){
//        int tomatch = 8;
//
//        Mockito.when(jdbcTemplate.update(
//                Mockito.anyString(),
//                Mockito.any(UserRowMapper.class)
//        )).thenReturn(tomatch);
//        User result = userDao.insertUser(new User(), null);
//        Assert.assertEquals(result, new User());
//    }

    @Test
    public void shouldLoginUser() throws Exception {

        User user = new User("Rishipal Singh","95rishipal@gmail.com","1","115640335689848772862","eyJhbGciOiJSUzI1NiIs");
        Mockito.when(userDao.findUser("95rishipal@gmail.com")).thenReturn(user);
        String token = "012345678901234567890";
        String json = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)
                .header("Token", token)
        ).andExpect(status().is(400));
    }

    @Test
    public void shouldUpdateToken() {
        int tomatch = 8;
        Mockito.when(jdbcTemplate.update(
                Mockito.anyString()
        )).thenReturn(tomatch);
        User result = userDao.updateUser(new User(), "");
        Assert.assertEquals(8, tomatch);
    }

//    @Test
//    public void shouldFindUserCatchExceptoin(){
//        Mockito.when(userDao.findUser("dhruvmiglani.23@gmail.com")).thenThrow(Exception.class);
//    }

}
