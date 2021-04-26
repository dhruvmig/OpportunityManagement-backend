package com.msau.opportunitymanagement.RowMapper;

import com.msau.opportunitymanagement.Models.Logs;
import com.msau.opportunitymanagement.Models.Opportunity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LogRowMapper implements RowMapper<Logs> {
    @Override
    public Logs mapRow(ResultSet rs, int rowNum) throws SQLException {
        Logs logs = new Logs();
//        logs.setNewOpp((Opportunity) rs.getObject("newOpp"));
//        logs.setOldOpp((Opportunity) rs.getObject("oldOpp"));
        logs.setNewOpp(rs.getString("newOpp"));
        logs.setOldOpp(rs.getString("oldOpp"));
        logs.setName(rs.getString("name"));
        logs.setUserId(rs.getString("userId"));
        logs.setDateTime(rs.getString("dateTime"));
        logs.setAction(rs.getString("action"));
        logs.setOpportunityId(rs.getInt("opportunityId"));
        logs.setLogId(rs.getInt("logId"));
        return logs;
    }
}
