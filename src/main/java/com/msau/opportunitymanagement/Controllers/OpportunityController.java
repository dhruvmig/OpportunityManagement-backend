package com.msau.opportunitymanagement.Controllers;
import java.util.ArrayList;
import java.util.List;

import com.msau.opportunitymanagement.Models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.msau.opportunitymanagement.DAO.*;
import com.msau.opportunitymanagement.Models.Opportunity;


@RestController
public class OpportunityController {
    private static final Logger log = LoggerFactory.getLogger(OpportunityController.class);

    @Autowired
    OpportunityDao OpportunityDao ;

    @GetMapping(path ="/opportunity")
    public List<Opportunity> getOpportunities(@RequestHeader(value="Authorization")String token) {
        log.info("Inside Get Opportunities Controller");
        try{
            String x = token.substring(8);
            List<Opportunity>loadOpportunities = new ArrayList<Opportunity>();
            loadOpportunities = OpportunityDao.getOpportunities();
            return loadOpportunities;
        }
        catch(Exception e)
        {
            log.error("Exception occured in get opportunities"+e);
            return null;
        }
    }
    @PostMapping(path="/opportunities/add/{currentUser}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Opportunity addOpportunity(@RequestBody Opportunity opportunity,@PathVariable("currentUser") String currentUser,@RequestHeader(value="Authorization")String token){
        log.info("Inside add opportunity controller");
        opportunity.setCreatedBy(currentUser);

        try{
            System.out.println("in try add");
            return OpportunityDao.addOpportunity(opportunity);
        }
        catch(Exception e)
        {
            log.error("Exception occured in add opportunities controller"+e);
            return null;
        }

    }

    @DeleteMapping(path="/opportunity/delete/{currentUser}/{id}")
    @ResponseBody
    public int deleteOpportunity(@PathVariable("id") int id,@PathVariable("currentUser")String currentUser,@RequestHeader(value="Authorization")String token){
            log.info("Inside delete opportunity controller");
        try{
            return OpportunityDao.deleteOpportunity(id,currentUser);
        }
        catch(Exception e)
        {
            log.error("Exception occured while deleting opportunity "+e);
                return 0;
        }

    }

    @PutMapping(path="/opportunity/update/{currentUser}/{id}")
    @ResponseBody
    public int updateOpportunity(@RequestBody Opportunity opportunity,@PathVariable("id")int id,@PathVariable("currentUser")String currentUser){
        log.info("inside update opportunity controller");
        try{
            opportunity.setId(id);
            System.out.println("opprutoien is "+opportunity);
            return OpportunityDao.updateOpportunity(opportunity,opportunity.getId(),currentUser);
        }
        catch(Exception e)
        {
            log.error("error to update opportunity "+e);
            return 0;
        }
    }

    @GetMapping(path="/opportunity/getCreatedBy/{id}")
    @ResponseBody
    public Opportunity getCreatedBy(@PathVariable("id") int id)
    {
        System.out.println("id in controller is "+id);
        return OpportunityDao.checkCreatedBy(id);
    }
}