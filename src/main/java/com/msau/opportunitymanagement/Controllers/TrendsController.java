package com.msau.opportunitymanagement.Controllers;


import com.msau.opportunitymanagement.DAO.TrendsDao;
import com.msau.opportunitymanagement.Models.Opportunity;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class TrendsController {

    @Autowired
    TrendsDao trendsDao;

    private static final Logger log =  LoggerFactory.getLogger(TrendsController.class);

    @GetMapping(path ="/trends/{trend}")
    public List<Map<String,String>> getTrends(@RequestHeader(value="Authorization")String token, @PathVariable("trend")String trend) {
        System.out.println("in controllers get trends skills"+trend);
        log.info("---- Inside get trends controller ----");
        return trendsDao.getSkills(trend);
    }
}
