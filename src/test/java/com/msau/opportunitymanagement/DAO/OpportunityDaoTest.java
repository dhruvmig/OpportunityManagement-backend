package com.msau.opportunitymanagement.DAO;


import com.msau.opportunitymanagement.Exceptions.NoRecordFound;
import com.msau.opportunitymanagement.Models.Opportunity;
import com.msau.opportunitymanagement.Models.User;
import com.msau.opportunitymanagement.RowMapper.OpportunityRowMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
//@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OpportunityDaoTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @Autowired
    MockMvc mockMvc;

    @Mock
    UserDaoImpl userDao;

    @Mock
    LogDaoImpl logDao;

    @InjectMocks
    OpportunityDAOImpl opportunityDAO;

    @Autowired
    OpportunityDAOImpl oppDao;

    private UserDaoImpl userDaoMock;
    private LogDaoImpl logDaoMock;
    int opp;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        User u = new User("Dhruv","dhruvmiglani.23@gmail.comm","102","fdlsfljsjlkjfjlsjflksjflsjlfjlsdkfs",null);
        System.out.println("user i s"+u);
        opp = opportunityDAO.addOpportunity(new Opportunity(1, "asd","java", "2014-05-3","bangalore", "Nothing ", "1021"));
        userDaoMock = Mockito.mock(UserDaoImpl.class);
        logDaoMock = Mockito.mock(LogDaoImpl.class);
        logDao = Mockito.mock(LogDaoImpl.class);
    }

    @Test
    public void shouldGetAllOppo() throws NoRecordFound {
        System.out.println("in dao test opp");
        ArrayList<Opportunity> list = new ArrayList<>();
        list.add(new Opportunity(1, "asd","java", "2014-05-3","bangalore", "Nothing ", "1021"));

        when(jdbcTemplate.query(
                Mockito.anyString(),
                Mockito.any(OpportunityRowMapper.class))).thenReturn(list);
        List<Opportunity> list2 = opportunityDAO.getAllOpportunities();
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void shouldGetActiveOppo() throws NoRecordFound {
        System.out.println("in dao test opp");
        ArrayList<Opportunity> list = new ArrayList<>();
        list.add(new Opportunity(1, "asd","java", "2014-05-3","bangalore", "Nothing ", "1021"));

        when(jdbcTemplate.query(
                Mockito.anyString(),
                Mockito.any(OpportunityRowMapper.class))).thenReturn(list);
        List<Opportunity> list2 = opportunityDAO.getOpportunities();
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void shouldInsertOppo() {
        User u = new User("dhruv","dhruvmiglani.23@gmail.com","1002","lsjflk",null);
        User x = userDao.insertUser(u,"lsjflk");
        when(jdbcTemplate.update(
                Mockito.anyString(),
                (Object[])Mockito.any()
        )).thenReturn(1);

        int result = opportunityDAO.addOpportunity(new Opportunity(1,"DM","java","2021-05-12","rajpura","description","1002"));
        System.out.println("result is     "+result);
        Assert.assertEquals(1, result);
    }

    @Test
    public void shouldDelOppo() throws NoRecordFound {

        int result = oppDao.deleteOpportunity(2876302,"104480558873341659614");
        Assert.assertEquals(1,result);
    }


    @Test
    public void shouldUpdateOppo() throws NoRecordFound {
        Mockito.when(jdbcTemplate.update(
                Mockito.anyString(),
                (Object[])Mockito.anyVararg()
        )).thenReturn(1);
        int result = oppDao.updateOpportunity(new Opportunity(),2895785,"12543");
        Assert.assertEquals(1, result);
    }

    @Test
    public void shouldDeactivateOppo() throws NoRecordFound {

        int result = oppDao.deactivateOpportunity(2895785,"104480558873341659614");
        Assert.assertEquals(1,result);
    }

    @Test
    public void checkCreatedBy() throws NoRecordFound {
        Opportunity x = oppDao.checkCreatedBy(2083096);
        Assert.assertEquals(x.getED(),"Dhruv");
    }

}
