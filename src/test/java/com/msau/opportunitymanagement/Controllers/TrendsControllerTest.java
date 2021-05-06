package com.msau.opportunitymanagement.Controllers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msau.opportunitymanagement.DAO.TrendsDaoImpl;
import com.msau.opportunitymanagement.DAO.UserDaoImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TrendsControllerTest {
    @Autowired
    public MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    public TrendsDaoImpl trendDaoImp;

    @MockBean
    public UserDaoImpl userDaoImp;

    @Before
    public void setup(){

    }

    @Test
    public void shouldGetDemand() throws Exception {
//        Mockito.when(trendDaoImp.getTrends("skills")).thenReturn(new ArrayList<Map<String,String>>());
        mockMvc.perform(get("/trends/{trend}","skills")
                .header("Authorization", "Bearer lsjdfslakjflsjlfkjslfjsdlfjsldkjflksdjflksjdflkjslkjfs")
        ).andExpect(status().isOk());
    }
    @Test
    public void shouldGetTrendsYearWise() throws Exception {
//        Mockito.when(trendDaoImp.getTrends("skills")).thenReturn(new ArrayList<Map<String,String>>());
        mockMvc.perform(get("/getTrends/{trend}","skills")
                .header("Authorization", "Bearer lsjdfslakjflsjlfkjslfjsdlfjsldkjflksdjflksjdflkjslkjfs")
        ).andExpect(status().isOk());
    }

}
