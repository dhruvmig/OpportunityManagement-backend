package com.msau.opportunitymanagement.RowMapper;

import com.msau.opportunitymanagement.Models.Logs;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LogRowMapper implements RowMapper<Logs> {
    @Override
    public Logs mapRow(ResultSet rs, int rowNum) throws SQLException {
        Logs logs = new Logs();

        logs.setUserId(rs.getString("userId"));
        logs.setDateTime(rs.getString("dateTime"));
        logs.setAction(rs.getString("action"));
        logs.setOpportunityId(rs.getInt("opportunityId"));
        logs.setLogId(rs.getInt("logId"));
        return logs;
    }
}
