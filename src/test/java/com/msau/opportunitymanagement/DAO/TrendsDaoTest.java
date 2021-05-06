package com.msau.opportunitymanagement.DAO;

import com.msau.opportunitymanagement.Exceptions.NoRecordFound;
import com.msau.opportunitymanagement.Models.Opportunity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TrendsDaoTest {
    @Mock
    JdbcTemplate jdbcTemplate;

    @Autowired
    MockMvc mockMvc;

    @Mock
    UserDaoImpl userDao;

    @Mock
    OpportunityDAOImpl opportunityDAO;

    @InjectMocks
    TrendsDaoImpl trendsDao;

    int opp;
    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
        opportunityDAO = Mockito.mock(OpportunityDAOImpl.class);
            Opportunity o = new Opportunity(1, "asd","java", "2014-05-3","bangalore", "Nothing ", "1021");

    }


    @Test
    public void shouldGetAllSkill() throws NoRecordFound {

        List<Map<String, String>> list = new ArrayList<Map<String,String>>();
//		Mockito.doThrow(new Exception()).when(jdbcTemplate).query(
//			    Mockito.anyString(),
//			    Mockito.any(RowCallbackHandler.class));
        int x = opportunityDAO.addOpportunity(new Opportunity());
        list = trendsDao.getSkills("skills");
        System.out.println(list);
        Assert.assertEquals(1,list.size()+1);
    }
    @Test
    public void shouldGetAllTrends() throws NoRecordFound {

        List<Map<String, String>> list = new ArrayList<Map<String,String>>();
//		Mockito.doThrow(new Exception()).when(jdbcTemplate).query(
//			    Mockito.anyString(),
//			    Mockito.any(RowCallbackHandler.class));
        trendsDao.getTrends("skills");
    }
}
