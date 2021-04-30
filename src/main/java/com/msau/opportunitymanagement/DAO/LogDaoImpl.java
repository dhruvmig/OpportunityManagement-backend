package com.msau.opportunitymanagement.DAO;

import com.msau.opportunitymanagement.Models.Logs;
import com.msau.opportunitymanagement.RowMapper.LogRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class LogDaoImpl implements LogDao {

    private static final Logger logger = LoggerFactory.getLogger(LogDaoImpl.class);

    private JdbcTemplate jdbcTemplate;
    public LogDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Logs addLog(Logs log) {
        logger.info("Inside add log");
        log.setLogId(new Random().nextInt(100));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        log.setDateTime(formatter.format(date));
        System.out.println("date is"+formatter.format(date) + log);
        String sql = "INSERT into logs(LogId,action,userId,opportunityId,dateTime,name,oldOpp,newOpp) VALUES (?,?,?,?,?,?,?,?)";
        try{
            int index = jdbcTemplate.update(sql, new Object[]{log.getLogId(), log.getAction(), log.getUserId(), log.getOpportunityId(), log.getDateTime(),log.getName(),log.getOldOpp(),log.getNewOpp()});
            System.out.println("log added");
            return log;
        }
        catch (Exception e)
        {
            System.out.println("exception to add log"+e);
            return null;
        }

    }

    @Override
    public List<Logs> getLogs() {
        logger.info("inside get all logs");
        String sql = "SELECT * from logs";
        try{
            List<Logs> logs = jdbcTemplate.query(sql,new LogRowMapper());
            return logs;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public List<Logs> getSpecificLogs(int id) {
        logger.info("inside get logs of particular opportunity ");
        String sql = "SELECT * from logs where opportunityId="+id+ ";";
        List<Logs> logs = jdbcTemplate.query(sql,new LogRowMapper());
        System.out.println("particular logs are "+logs + id);
        return logs;
    }


}
