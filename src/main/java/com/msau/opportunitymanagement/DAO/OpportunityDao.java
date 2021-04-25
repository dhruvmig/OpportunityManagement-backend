package com.msau.opportunitymanagement.DAO;

import java.util.List;

import com.msau.opportunitymanagement.Models.Opportunity;

public interface OpportunityDao {

    List<Opportunity> getOpportunities();

    Opportunity addOpportunity(Opportunity opportunity);

    int deleteOpportunity(int id,String currentUser);

    int updateOpportunity(Opportunity opportunity,int id,String currentUser);

    String checkCreatedBy(int id);

}