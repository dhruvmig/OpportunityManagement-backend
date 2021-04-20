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
    public List<Opportunity> getOpportunities() {
        System.out.println("in controllers");
        List<Opportunity>loadOpportunities = new ArrayList<Opportunity>();
        loadOpportunities = OpportunityDao.getOpportunities();
		return loadOpportunities;
    }
    @PostMapping(path="/opportunities/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public int addOpportunity(@RequestBody Opportunity opportunity){
        System.out.println("IN POST");
        return OpportunityDao.addOpportunity(opportunity);
    }

    @DeleteMapping(path="/opportunity/delete/{id}")
    @ResponseBody
    public int deleteOpportunity(@PathVariable("id") int id){
        return OpportunityDao.deleteOpportunity(id);
    }

    @PutMapping(path="/opportunity/update/{id}")
    @ResponseBody
    public int updateOpportunity(@RequestBody Opportunity opportunity,@PathVariable("id")Long id){
//        return 0;+
        opportunity.setId(id);
        System.out.println("opprutoien is "+opportunity);
        return OpportunityDao.updateOpportunity(opportunity,opportunity.getId());
    }
}