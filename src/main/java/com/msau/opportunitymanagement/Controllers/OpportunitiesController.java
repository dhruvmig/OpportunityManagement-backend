package com.msau.opportunitymanagement.Controllers;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.msau.opportunitymanagement.Exceptions.NoRecordFound;
import com.msau.opportunitymanagement.Models.User;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.msau.opportunitymanagement.DAO.*;
import com.msau.opportunitymanagement.Models.Opportunity;

@RestController
public class OpportunitiesController {
    private static final Logger log = LoggerFactory.getLogger(OpportunitiesController.class);

    @Autowired
    OpportunityDao OpportunityDao ;

    @GetMapping(path ="/opportunities/active")
    public List<Opportunity> getActiveOpportunities(@RequestHeader(value="Authorization")String token) throws NoRecordFound {
        log.info("Inside Get Opportunities Controller");
        return OpportunityDao.getOpportunities();
    }

    @GetMapping(path ="/opportunities/all")
    public List<Opportunity> getAllOpportunities(@RequestHeader(value="Authorization")String token) {
        log.info("Inside Get Opportunities Controller");
        return OpportunityDao.getAllOpportunities();
    }

    @PostMapping(path="/opportunity/add/{currentUser}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public int addOpportunity(@RequestBody Opportunity opportunity,@PathVariable("currentUser") String currentUser,@RequestHeader(value="Authorization")String token){
        log.info("Inside add opportunity controller");
        opportunity.setCreatedBy(currentUser);
        opportunity.setStatus("active");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        opportunity.setUpdatedAt(dtf.format(now));
        System.out.println("in try add"+opportunity.getStatus()+opportunity.getUpdatedAt());
        int result= OpportunityDao.addOpportunity(opportunity);
        return result;
    }


    @DeleteMapping(path="/opportunities/delete/{currentUser}/{id}")
    @ResponseBody
    public int deleteOpportunity(@PathVariable("id") int id,@PathVariable("currentUser")String currentUser,@RequestHeader(value="Authorization")String token) throws NoRecordFound {
        log.info("Inside delete opportunity controller");
        return OpportunityDao.deleteOpportunity(id,currentUser);
    }

    @PutMapping(path="/opportunities/update/{currentUser}/{id}")
    @ResponseBody
    public int updateOpportunity(@RequestBody Opportunity opportunity,@PathVariable("id")int id,@PathVariable("currentUser")String currentUser) throws NoRecordFound {
        log.info("inside update opportunity controller");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        opportunity.setUpdatedAt(dtf.format(now));
        System.out.println("current user is "+currentUser);
        opportunity.setId(id);
        System.out.println("opprutoien is "+opportunity);
        return OpportunityDao.updateOpportunity(opportunity,opportunity.getId(),currentUser);}


    @GetMapping(path="/opportunities/getCreatedBy/{id}")
    @ResponseBody
    public Opportunity getCreatedBy(@PathVariable("id") int id) throws NoRecordFound {
        System.out.println("id in controller is "+id);
        return OpportunityDao.checkCreatedBy(id);
    }

    @PostMapping(path="/opportunities/deactivate/{currentUser}/{id}")
    @ResponseBody
    public int deactivateOpportunity(@PathVariable("id") int id,@PathVariable("currentUser")String currentUser,@RequestHeader(value="Authorization")String token) throws NoRecordFound {
        log.info("Inside deactivate opportunity controller");
        return OpportunityDao.deactivateOpportunity(id,currentUser);}
}
