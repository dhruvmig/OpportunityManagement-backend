package com.msau.opportunitymanagement.Controllers;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msau.opportunitymanagement.DAO.OpportunityDAOImpl;
import com.msau.opportunitymanagement.DAO.UserDaoImpl;
import com.msau.opportunitymanagement.Exceptions.NoRecordFound;
import com.msau.opportunitymanagement.Models.Opportunity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class OpportunitiesControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    public OpportunityDAOImpl opportunityDaoImp;

    @MockBean
    public UserDaoImpl userDaoImp;

    @Autowired
    private ObjectMapper objectMapper;

    public Opportunity oppoDefault;

    @Before
    public void setup(){
        LocalDate now = LocalDate.now();
        oppoDefault = new Opportunity(1,"Dhruv","Java","date","locatoin","description","100");
    }

    @Test
    public void shouldGetActiveOpportunity() throws Exception {
        System.out.println("in test get opp");
        mockMvc.perform(get("/opportunities/active").header("Authorization", "Bearer ljdsljflsjlfjlsjdljflsdjlfsjd")).andExpect(status().is(200));
    }

    @Test
    public void shouldGetAllOpportunity() throws Exception {
        System.out.println("in test get opp");
        mockMvc.perform(get("/opportunities/all").header("Authorization", "Bearer ljdsljflsjlfjlsjdljflsdjlfsjd")).andExpect(status().is(200));
    }

    @Test
    public void shouldAddOpportunity() throws Exception{
        Opportunity opp = new Opportunity();
        String json = objectMapper.writeValueAsString(opp);
        mockMvc.perform(post("/opportunity/add/{currentUser}","1000")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)
                .header("Authorization", "Bearer ljdsljflsjlfjlsjdljflsdffffffffffffjlfsjd")
        ).andExpect(status().is(200));
    }

    @Test
    public void shoulddelOpportunity() throws Exception {
        mockMvc.perform(delete("/opportunities/delete/{currentUser}/{id}","1","1000175")
                .header("Authorization", "Bearer ljdsljflsjlfjlsjdljflsdffffffffffffjlfsjd")
        ).andExpect(status().is(200));
    }


    @Test
    public void shouldUpdateOpportunity() throws Exception{
        Opportunity opp = new Opportunity();
        String json = objectMapper.writeValueAsString(opp);
        mockMvc.perform(put("/opportunities/update/{currentUser}/{id}","1","1000175")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)
                .header("Authorization", "Bearer ljdsljflsjlfjlsjdljflsdffffffffffffjlfsjd")
        ).andExpect(status().is(200));
    }

    @Test
    public void shouldGetCreatedBy() throws Exception {
        mockMvc.perform(get("/opportunities/getCreatedBy/{id}","1")
        )
                .andExpect(status().is(200));
    }

    @Test
    public void shoulddeactivateOpportunity() throws Exception {
        mockMvc.perform(post("/opportunities/deactivate/{currentUser}/{id}","1","1000175")
                .header("Authorization", "Bearer ljdsljflsjlfjlsjdljflsdffffffffffffjlfsjd")
        ).andExpect(status().is(200));
    }

}
