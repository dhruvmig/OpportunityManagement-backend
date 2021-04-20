package com.msau.opportunitymanagement.DAO;

import java.util.List;

import com.msau.opportunitymanagement.Models.Opportunity;

public interface OpportunityDao {

    List<Opportunity> getOpportunities();

    int addOpportunity(Opportunity opportunity);

    int deleteOpportunity(int id);

    int updateOpportunity(Opportunity opportunity,Long id);

}