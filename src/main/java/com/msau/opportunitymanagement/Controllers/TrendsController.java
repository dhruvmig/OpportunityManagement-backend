package com.msau.opportunitymanagement.Controllers;


import com.msau.opportunitymanagement.DAO.TrendsDao;
import com.msau.opportunitymanagement.Models.Opportunity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class TrendsController {

    @Autowired
    TrendsDao trendsDao;

    @GetMapping(path ="/trends/{trend}")
    public List<Map<String,String>> getTrends(@RequestHeader(value="Authorization")String token, @PathVariable("trend")String trend) {
        System.out.println("in controllers get trends skills"+trend);
//        System.out.println("her in controller calling doa"+trendsDao.getSkills(trend));
        return trendsDao.getSkills(trend);
    }
}
