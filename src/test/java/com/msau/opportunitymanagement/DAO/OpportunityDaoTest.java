package com.msau.opportunitymanagement.DAO;


import com.msau.opportunitymanagement.Exceptions.NoRecordFound;
import com.msau.opportunitymanagement.Models.Opportunity;
import com.msau.opportunitymanagement.RowMapper.OpportunityRowMapper;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class OpportunityDaoTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    OpportunityDAOImpl opportunityDAO;

    @Test
    public void shouldGetAllOppo() throws NoRecordFound {

        ArrayList<Opportunity> list = new ArrayList<>();
        list.add(new Opportunity(1, "asd","java", "2014-05-3","bangalore", "Nothing ", "1021"));

        Mockito.when(jdbcTemplate.query(
                Mockito.anyString(),
                Mockito.any(OpportunityRowMapper.class))).thenReturn(list);
        List<Opportunity> list2 = opportunityDAO.getOpportunities();
        Assert.assertEquals(list2.size(), list.size());
    }
}
