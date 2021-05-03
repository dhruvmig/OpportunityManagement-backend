package com.msau.opportunitymanagement.DAO;


import com.msau.opportunitymanagement.Exceptions.NoRecordFound;
import com.msau.opportunitymanagement.Models.Opportunity;
import com.msau.opportunitymanagement.Models.User;
import com.msau.opportunitymanagement.RowMapper.OpportunityRowMapper;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OpportunityDaoTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @Autowired
    MockMvc mockMvc;

    @Mock
    UserDaoImpl userDao;

    @InjectMocks
    OpportunityDAOImpl opportunityDAO;


    @Test
    public void shouldGetAllOppo() throws NoRecordFound {
        System.out.println("in dao test opp");
        ArrayList<Opportunity> list = new ArrayList<>();
        list.add(new Opportunity(1, "asd","java", "2014-05-3","bangalore", "Nothing ", "1021"));

        Mockito.when(jdbcTemplate.query(
                Mockito.anyString(),
                Mockito.any(OpportunityRowMapper.class))).thenReturn(list);
        List<Opportunity> list2 = opportunityDAO.getOpportunities();
        Assert.assertEquals(list2.size(), list.size());
    }

//    @Test
//    public void shouldInsertOppo() {
//        Opportunity tomatch = new Opportunity();
//        Mockito.when(jdbcTemplate.update(
//                Mockito.anyString(),
//                (Object[])Mockito.any()
//        )).thenReturn(tomatch);
//        Opportunity result = opportunityDAO.addOpportunity(new Opportunity());
//        Assert.assertEquals(result, tomatch);
//    }

//    @Test
//    public void shouldDelOppo() {
//        int tomatch = 8;
////        userDao.insertUser(new User("dhruv","dmiglani@gmail.com","104480558873341659614","lskdjfldsjlfjsldjflssjdflksjdf",null),"lskdjfldsjlfjsldjflssjdflksjdf");
//        Mockito.when(jdbcTemplate.update(
//                Mockito.anyString(),
//                (Object[])Mockito.any()
//        )).thenReturn(tomatch);
//        int result =opportunityDAO.deleteOpportunity(1,"104480558873341659614");
//        Assert.assertEquals(result, tomatch);
//    }
}
