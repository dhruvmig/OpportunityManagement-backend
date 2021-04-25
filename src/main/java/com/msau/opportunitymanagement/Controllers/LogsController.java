package com.msau.opportunitymanagement.Controllers;

import com.msau.opportunitymanagement.DAO.LogDao;
import com.msau.opportunitymanagement.Models.Logs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LogsController {

    private static final Logger logger = LoggerFactory.getLogger(LogsController.class);
    @Autowired
    LogDao logsDao;

    @GetMapping(path = "/audits")
    public List<Logs> getAllLogs()
    {
        logger.info("inside get all logs controller");
        List<Logs> logs = new ArrayList<>();
        logs = logsDao.getLogs();

        return logs;
    }

    @PostMapping(path="/addAudit")
    public int addLogs(@RequestBody Logs logs){
        logger.info("inside add logs controller");
        Logs l = logsDao.addLog(logs);

        return 1;
    }
}
