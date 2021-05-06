package com.msau.opportunitymanagement.DAO;

import java.util.List;

import com.msau.opportunitymanagement.Exceptions.NoRecordFound;
import com.msau.opportunitymanagement.Models.Opportunity;
import com.msau.opportunitymanagement.Models.User;

public interface OpportunityDao {

    List<Opportunity> getOpportunities() throws NoRecordFound;

    int addOpportunity(Opportunity opportunity);

    int deleteOpportunity(int id,String currentUser);

    int deactivateOpportunity(int id,String currentUser);

    public List<Opportunity> getAllOpportunities();

    int updateOpportunity(Opportunity opportunity,int id,String currentUser);

    Opportunity checkCreatedBy(int id) throws NoRecordFound;

}