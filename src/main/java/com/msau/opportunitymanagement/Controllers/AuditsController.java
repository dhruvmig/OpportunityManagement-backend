package com.msau.opportunitymanagement.Controllers;

import com.msau.opportunitymanagement.DAO.LogDao;
import com.msau.opportunitymanagement.Models.Logs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AuditsController {
    private static final Logger logger = LoggerFactory.getLogger(AuditsController.class);
    @Autowired
    LogDao logsDao;

    @GetMapping(path = "/audits/all")
    public List<Logs> getAllLogs()
    {
        logger.info("inside get all logs controller");
        List<Logs> logs = new ArrayList<>();
        logs = logsDao.getLogs();
        return logs;
    }

    @PostMapping(path="/addAudits")
    public int addLogs(@RequestBody Logs logs){
        logger.info("inside add logs controller");
        int l = logsDao.addLog(logs);
        return l;
    }

    @GetMapping(path="/getAudit/{id}")
    public List<Logs> getLogs(@PathVariable("id")int id)
    {
        List<Logs> logs = new ArrayList<>();
        logs = logsDao.getSpecificLogs(id);
        return logs;
    }
}
