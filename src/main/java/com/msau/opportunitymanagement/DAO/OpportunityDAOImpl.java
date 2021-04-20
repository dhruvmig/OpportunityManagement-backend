package com.msau.opportunitymanagement.DAO;

import com.msau.opportunitymanagement.Models.Opportunity;
import com.msau.opportunitymanagement.RowMapper.OpportunityRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OpportunityDAOImpl implements OpportunityDao{

    private static final Logger log = LoggerFactory.getLogger(OpportunityDAOImpl.class);

    private JdbcTemplate jdbcTemplate;
    public OpportunityDAOImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Opportunity> getOpportunities() {
        String sql="SELECT * FROM opportunity;";
        List<Opportunity> opportunityList = jdbcTemplate.query(sql,new OpportunityRowMapper());
//		opportunityList = query;
        return opportunityList;
    }

    @Override
    public int addOpportunity(Opportunity opportunity) {
        String sql  = "INSERT into opportunity(ed,date,location,skills) VALUES (?,?,?,?);";
        int index = jdbcTemplate.update(sql, new Object[]{opportunity.getDate(), opportunity.getLocation(), opportunity.getSkills(), opportunity.getED()});
        return index;

    }

    @Override
    public int deleteOpportunity(int id) {
        String sql = "DELETE FROM opportunity WHERE id =?";
        int index = jdbcTemplate.update(sql, new Object[]{id});
        return index;
    }

    @Override
    public int updateOpportunity(Opportunity opportunity) {
        System.out.println("in dao it is "+opportunity);
        String sql = "UPDATE opportunity SET  date=?, skills=?, location=?, ed=?";
        int index = jdbcTemplate.update(sql, new Object[]{opportunity.getDate(), opportunity.getLocation(), opportunity.getSkills(), opportunity.getED()});
        return index;
    }
}
