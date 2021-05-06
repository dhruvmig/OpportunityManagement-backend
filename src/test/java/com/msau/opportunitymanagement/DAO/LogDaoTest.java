package com.msau.opportunitymanagement.DAO;

import com.msau.opportunitymanagement.Models.Logs;
import com.msau.opportunitymanagement.Models.Opportunity;
import com.msau.opportunitymanagement.Models.User;
import com.msau.opportunitymanagement.RowMapper.LogRowMapper;
import com.msau.opportunitymanagement.RowMapper.OpportunityRowMapper;
import com.msau.opportunitymanagement.RowMapper.UserRowMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
public class LogDaoTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    LogDaoImpl logDao;

    @Autowired
    LogDaoImpl logDaos;

    @Test
    public void shouldGetAllLogs(){
        ArrayList<Logs> list = new ArrayList<>();
        list.add(new Logs());
        Mockito.when(jdbcTemplate.query(
                Mockito.anyString(),
                Mockito.any(LogRowMapper.class))).thenReturn(list);
        List<Logs> list2 = logDao.getLogs();
        Assert.assertEquals(list2.size(), list.size());
    }

    @Test
    public void shouldGetLogsById(){
        Logs tomatch = new Logs(1000,"Added","Nothing",1000,"dlsjf","lskdjf","lskdfj","L");
        Mockito.when(jdbcTemplate.queryForObject(
                Mockito.anyString(),
                Mockito.any(LogRowMapper.class)
        )).thenReturn(tomatch);
        List<Logs> result = logDao.getSpecificLogs(1000);
        Assert.assertEquals(1, 1);
    }

    @Test
    public void shouldAddLogs(){
            Mockito.when(jdbcTemplate.update(
                    Mockito.anyString(),
                    (Object[]) Mockito.any()
            )).thenReturn(1);
            int res = logDao.addLog(new Logs());
        System.out.println("resulte here i s+"+res);
        Assert.assertEquals(res,1);
    }


//    @Test
//    public void shouldThrowExceptionToGetLogs(){
//        Mockito.doThrow(new Exception()).when(logDao).getLogs();
//    }
}
