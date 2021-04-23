package com.msau.opportunitymanagement.Controllers;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.msau.opportunitymanagement.DAO.*;
import com.msau.opportunitymanagement.Models.Opportunity;


@RestController
public class OpportunityController {

    @Autowired
    OpportunityDao OpportunityDao ;

    @GetMapping(path ="/opportunity")
    public List<Opportunity> getOpportunities(@RequestHeader(value="Authorization")String token) {
        System.out.println("in controllers");
        try{
            String x = token.substring(8);
            List<Opportunity>loadOpportunities = new ArrayList<Opportunity>();
            loadOpportunities = OpportunityDao.getOpportunities();
            return loadOpportunities;
        }
        catch(Exception e)
        {
            System.out.println("exception in get opp"+e);
            return null;
        }


    }
    @PostMapping(path="/opportunities/add/{currentUser}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Opportunity addOpportunity(@RequestBody Opportunity opportunity,@PathVariable("currentUser") String currentUser,@RequestHeader(value="Authorization")String token){
//        System.out.println("IN POST"+opportunity);
        opportunity.setCreatedBy(currentUser);
        System.out.println("IN POST"+opportunity+currentUser);
        try{
            System.out.println("in try add");
            return OpportunityDao.addOpportunity(opportunity);
        }
        catch(Exception e)
        {
            return null;
        }

    }

    @DeleteMapping(path="/opportunity/delete/{id}")
    @ResponseBody
    public int deleteOpportunity(@PathVariable("id") int id,@RequestHeader(value="Authorization")String token){
        try{
            return OpportunityDao.deleteOpportunity(id);
        }
        catch(Exception e)
        {
                return 0;
        }

    }

    @PutMapping(path="/opportunity/update/{id}")
    @ResponseBody
    public int updateOpportunity(@RequestBody Opportunity opportunity,@PathVariable("id")int id){
        try{
            opportunity.setId(id);
            System.out.println("opprutoien is "+opportunity);
            return OpportunityDao.updateOpportunity(opportunity,opportunity.getId());
        }
        catch(Exception e)
        {
            return 0;
        }
//        return 0;+

    }

    @GetMapping(path="/opportunity/getCreatedBy/{id}")
    @ResponseBody
    public String getCreatedBy(@PathVariable("id") int id)
    {
        System.out.println("id in controller is "+id);
        return OpportunityDao.checkCreatedBy(id);
    }
}