package com.msau.opportunitymanagement.Controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.msau.opportunitymanagement.DAO.OpportunityDAOImpl;
import com.msau.opportunitymanagement.Models.Opportunity;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

public class OpportunityControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    public OpportunityDAOImpl opportunityDaoImp;

//    @Test
//    public void shouldGetAllOpportunity() throws Exception {
//        Mockito.when(opportunityDaoImp.getOpportunities()).thenReturn(new ArrayList<Opportunity>());
//        mockMvc.perform(get("/opportunity").header("Authorizarion", "Bearer ljdsljflsjlfjlsjdljflsdjlfsjd")).andExpect(status().isOk());
//    }
}
