package com.msau.opportunitymanagement.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.msau.opportunitymanagement.Models.Opportunity;

public class OpportunityRowMapper implements RowMapper<Opportunity> {

    @Override
    public Opportunity mapRow(ResultSet rs, int rowNum) throws SQLException {
        // TODO Auto-generated method stub
        Opportunity opportunity = new Opportunity();

        opportunity.setId(rs.getInt("id"));
        opportunity.setDate(rs.getString("date"));
        opportunity.setED(rs.getString("ed"));
        opportunity.setLocation(rs.getString("location"));
        opportunity.setSkills(rs.getString("skills"));
        opportunity.setDescription(rs.getString("description"));
        opportunity.setCreatedBy(rs.getString("createdBy"));
        return opportunity;
    }

}
