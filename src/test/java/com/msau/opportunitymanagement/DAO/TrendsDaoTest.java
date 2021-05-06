package com.msau.opportunitymanagement.DAO;

import com.fasterxml.jackson.core.ObjectCodec;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.lang.reflect.Array;
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

    @Autowired
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

        list = trendsDao.getSkills("skills");
        System.out.println(list);
        Assert.assertEquals(2,list.size());
    }
    @Test
    public void shouldGetAllTrends() throws NoRecordFound {
        System.out.println("hereeee");
        List<Map<String, String>> list = new ArrayList<Map<String,String>>();
        System.out.println("theereeee");
//        ArrayNode x = mapper.createArrayNode();
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode x = mapper.createArrayNode();
        x = trendsDao.getTrends("skills");
        System.out.println("now "+x);
        Assert.assertEquals(4,x.size());
    }
}
