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
        System.out.println("date is"+formatter.format(date));
        log.setDateTime(formatter.format(date));
        String sql = "INSERT into logs(LogId,action,userId,opportunityId,dateTime) VALUES (?,?,?,?,?)";
        int index = jdbcTemplate.update(sql, new Object[]{log.getLogId(), log.getAction(), log.getUserId(), log.getOpportunityId(), log.getDateTime()});

        return log;
    }

    @Override
    public List<Logs> getLogs() {
        logger.info("inside get all logs");
        String sql = "SELECT * from logs";
        List<Logs> logs = jdbcTemplate.query(sql,new LogRowMapper());
        return logs;
    }
}
