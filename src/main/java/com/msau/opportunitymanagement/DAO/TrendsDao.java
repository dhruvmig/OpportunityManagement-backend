package com.msau.opportunitymanagement.DAO;

//import jdk.internal.net.http.common.Pair;

import java.util.List;
import java.util.Map;

public interface TrendsDao {
    public List<Map<String,String>> getSkills(String trend);
}
