package com.msau.opportunitymanagement.DAO;

//import jdk.internal.net.http.common.Pair;

import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.List;
import java.util.Map;

public interface TrendsDao {
    public List<Map<String,String>> getSkills(String trend);

    public ArrayNode getTrends(String trend);
}
