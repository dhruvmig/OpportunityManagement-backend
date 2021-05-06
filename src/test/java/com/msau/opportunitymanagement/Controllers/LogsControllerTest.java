package com.msau.opportunitymanagement.Controllers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msau.opportunitymanagement.DAO.LogDaoImpl;
import com.msau.opportunitymanagement.DAO.UserDaoImpl;
import com.msau.opportunitymanagement.Exceptions.NoRecordFound;
import com.msau.opportunitymanagement.Models.Logs;
import com.msau.opportunitymanagement.Models.User;
import org.junit.Before;
import org.junit.Test;
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

@RunWith(SpringRunner.class)
//@WebMvcTest(controllers = {LogsController.class})
@AutoConfigureMockMvc
@SpringBootTest
public class LogsControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    public UserDaoImpl userDaoImp;

    @MockBean
    public LogDaoImpl logDaoImp;

    @Before
    public void setup(){

    }

    @Test
    public void shouldGetLang() throws Exception {
        Mockito.when(logDaoImp.getLogs()).thenReturn(new ArrayList<Logs>());
        mockMvc.perform(get("/audits")).andExpect(status().isOk());
    }


    @Test
    public void shouldSearch() throws Exception {
        Mockito.when(logDaoImp.getSpecificLogs(0)).thenReturn(new ArrayList<Logs>());
        mockMvc.perform(get("/getAudits/0")).andExpect(status().isOk());
    }

    @Test
    public void shouldAddLogs() throws Exception{
        Logs user = new Logs();
        String token = "Bearer 01234567890123456789sdfsdff0";
        String json = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/addAudit")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)
        ).andExpect(status().is(200));
    }

}
