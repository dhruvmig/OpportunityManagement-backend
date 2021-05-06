package com.msau.opportunitymanagement.Controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msau.opportunitymanagement.DAO.UserDaoImpl;
import com.msau.opportunitymanagement.Models.User;
import org.junit.Before;
import org.junit.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
//@WebMvcTest(controllers = {UserController.class})
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    public MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    public UserDaoImpl userDao;

    public List<User> userList;

    @Before
    public void setup(){
        this.userList = new ArrayList<>();
        this.userList.add(new User("dhruv","d@g.com","1",null,null));
        this.userList.add(new User("abc","hello@gmail.com","2",null,null));
    }

    @Test
    public void getUserById() throws Exception {
        Mockito.when(userDao.getUserName("5")).thenReturn(String.valueOf(userList.get(0)));
        mockMvc.perform(post("/getUser").contentType(MediaType.ALL)
                .content("5")).andExpect(status().isOk());
    }


    @Test
    public void shouldLoginWhenUserPresent() throws Exception {
        Mockito.when(userDao.updateUser(null, null)).thenReturn(new User());
        Mockito.when(userDao.insertUser(null, null)).thenReturn(new User());
        User user = new User("name","email","id","012345678901234567890",null);
        Mockito.when(userDao.findUser("email")).thenReturn(user);
        String token = "Bearer 01234567890123456789sdfsdff0";
        String json = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)
                .header("Authorization", token)
        ).andExpect(status().is(200));
    }

    @Test
    public void shouldLoginWhenUserNotPresent() throws Exception {
        User user = new User();
        String token = "Bearer 01234567890123456789sdfsdff0";
        String json = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)
                .header("Authorization", token)
        ).andExpect(status().is(200));
    }
}
