package com.msau.opportunitymanagement.DAO;

import com.msau.opportunitymanagement.Exceptions.NoRecordFound;
import com.msau.opportunitymanagement.Models.Logs;
import com.msau.opportunitymanagement.Models.Opportunity;
import com.msau.opportunitymanagement.RowMapper.OpportunityRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class OpportunityDAOImpl implements OpportunityDao{

    private static final Logger log = LoggerFactory.getLogger(OpportunityDAOImpl.class);

    @Autowired
    LogDao logDao;

    @Autowired
    UserDao userDao;

    private final JdbcTemplate jdbcTemplate;
    public OpportunityDAOImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Opportunity> getOpportunities() throws NoRecordFound {
        String sql="SELECT * FROM opportunity;";
        log.info("Inside Get Opportunities DAO");
        List<Opportunity>opportunityList=new ArrayList<>();
        try{
             opportunityList = jdbcTemplate.query(sql,new OpportunityRowMapper());
        }
        catch (Exception e)
        {
            throw (NoRecordFound)e;
        }
        return opportunityList;
    }

    @Override
    public Opportunity addOpportunity(Opportunity opportunity) {
        log.info("Inside add Opportunity DAO");
        try{
            String sql1="SELECT * FROM opportunity;";

            List<Opportunity> opportunities=jdbcTemplate.query(sql1,new OpportunityRowMapper());
            Random r = new Random( System.currentTimeMillis() );
            int id = ((1 + r.nextInt(2)) * 1000000 + r.nextInt(1000000));
            opportunity.setId(id);
            System.out.println("id is "+id);
//            System.out.println("Your UUID is: " + variant);
//            if(opportunities.size()==0)
//            {
//                opportunity.setId(1);
//            }
//            else
//            {
//                opportunity.setId(opportunities.get(opportunities.size()-1).getId()+1);
//            }
            System.out.println("id is "+opportunity.getId());
            String sql  = "INSERT into opportunity(id,ed,date,location,skills,description,createdBy) VALUES (?,?,?,?,?,?,?);";

            jdbcTemplate.update(sql, new Object[]{opportunity.getId(),opportunity.getED(), opportunity.getDate(), opportunity.getLocation(), opportunity.getSkills(),opportunity.getDescription(),opportunity.getCreatedBy() });

            Logs newLog = new Logs();
            String userName = userDao.getUserName(opportunity.getCreatedBy());

            newLog.setName(userName);
            newLog.setOpportunityId(opportunity.getId());
            newLog.setNewOpp(opportunity.toString());
            newLog.setOldOpp("null");
            newLog.setAction("Added new Opportunity");

            newLog.setUserId(opportunity.getCreatedBy());

            logDao.addLog(newLog);

            return opportunity;
        }
        catch (Exception e)
        {
            log.error("exception to add opporutnity"+e);
            return null;
        }


    }

    @Override
    public int deleteOpportunity(int id,String currentUser) {
        log.info("inside delete opportunity DAO");
        String sql = "DELETE FROM opportunity WHERE id =?";
        Logs newLog = new Logs();
        newLog.setUserId(currentUser);
        String userName = userDao.getUserName(currentUser);

        newLog.setName(userName);
        Opportunity x = checkCreatedBy(id);
        newLog.setOldOpp(x.toString());
        newLog.setNewOpp("null");
        System.out.println("current user is "+currentUser);
        int index = jdbcTemplate.update(sql, new Object[]{id});


        newLog.setOpportunityId(id);

        newLog.setAction("Deleted this Opportunity");


        System.out.println("current log is "+newLog);
        logDao.addLog(newLog);
        return index;
    }

    @Override
    public int updateOpportunity(Opportunity opportunity,int id,String currentUser) {
        log.info("inside update opportunity DAO");
        String sql = "UPDATE opportunity SET  date=?, skills=?, location=?, ed=? ,description=?,createdBy=? where id=?";

        try{
            Opportunity x = checkCreatedBy(id);
            int index = jdbcTemplate.update(sql, new Object[]{opportunity.getDate(),  opportunity.getSkills(), opportunity.getLocation(),opportunity.getED(),opportunity.getDescription(),opportunity.getCreatedBy(),id});

            Logs newLog = new Logs();


            newLog.setNewOpp(opportunity.toString());
            newLog.setOldOpp(x.toString());
            newLog.setOpportunityId(id);

            newLog.setAction("Updated this Opportunity");

            newLog.setUserId(currentUser);
            String userName = userDao.getUserName(currentUser);
            newLog.setName(userName);
            logDao.addLog(newLog);
            return index;
        }
        catch(Exception e)
        {
            return 0;
        }

    }

    @Override
    public Opportunity checkCreatedBy(int id) {
        log.info("inside check created by DAO");
        String sql = "SELECT * FROM opportunity WHERE id="+id+";";
        System.out.println("sql in doa created by check is "+sql);
        try{
            Opportunity x= jdbcTemplate.queryForObject(sql,new OpportunityRowMapper());
            System.out.println("ollllllllllllllllllllllllllllllllllll"+x);
           return x;
        }
        catch (Exception e) {
            System.out.println("exception to check created by " + e);
            return null;
        }

    }
}
