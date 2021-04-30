package com.msau.opportunitymanagement.DAO;

import com.msau.opportunitymanagement.Models.Logs;

import java.util.List;

public interface LogDao {

    public Logs addLog(Logs log);
    public List<Logs> getLogs();

    public List<Logs> getSpecificLogs(int id);
}
