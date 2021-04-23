package com.msau.opportunitymanagement.DAO;

import com.msau.opportunitymanagement.Models.Opportunity;
import com.msau.opportunitymanagement.RowMapper.OpportunityRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

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
    public Opportunity addOpportunity(Opportunity opportunity) {
        int generatedLong = new Random().nextInt(100);
        opportunity.setId(generatedLong);
        String sql  = "INSERT into opportunity(id,ed,date,location,skills,description,createdBy) VALUES (?,?,?,?,?,?,?);";
        System.out.println("added opp is "+opportunity);
        int index = jdbcTemplate.update(sql, new Object[]{opportunity.getId(),opportunity.getED(), opportunity.getDate(), opportunity.getLocation(), opportunity.getSkills(),opportunity.getDescription(),opportunity.getCreatedBy() });
        System.out.println("in add dao"+opportunity);
        return opportunity;

    }

    @Override
    public int deleteOpportunity(int id) {
        String sql = "DELETE FROM opportunity WHERE id =?";
        int index = jdbcTemplate.update(sql, new Object[]{id});
        return index;
    }

    @Override
    public int updateOpportunity(Opportunity opportunity,int id) {
        System.out.println("in dao it is "+opportunity);
        String sql = "UPDATE opportunity SET  date=?, skills=?, location=?, ed=? ,description=? where id=?";
        int index = jdbcTemplate.update(sql, new Object[]{opportunity.getDate(), opportunity.getLocation(), opportunity.getSkills(), opportunity.getED(),opportunity.getDescription(),id});
        System.out.println("index is "+index);
        return index;
    }

    @Override
    public String checkCreatedBy(int id) {

        String sql = "select createdBy from opportunity where id='"+id+"';";

        try{
            Opportunity x= jdbcTemplate.queryForObject(sql,new OpportunityRowMapper());
            System.out.println("opp in dao created by is "+x);
            return x.getCreatedBy();

        }
        catch (Exception e) {
            System.out.println("exception to check created by " + e);
            return null;
        }

    }
}
