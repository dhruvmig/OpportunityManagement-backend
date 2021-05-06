package com.msau.opportunitymanagement.DAO;

//import jdk.internal.net.http.common.Pair;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TrendsDaoImpl implements TrendsDao{

    private static final Logger logger = LoggerFactory.getLogger(TrendsDaoImpl.class);

    private JdbcTemplate jdbcTemplate;
    public TrendsDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Map<String, String>> getSkills(String trend) {
        logger.info("inside get Trends DAO");
        String sql="SELECT "+trend+", count(*) FROM opportunity GROUP BY "+trend+"; ";
        System.out.println(sql);
        List<Map<String,String>> item = new ArrayList<>();
        try{
            jdbcTemplate.query(sql, new RowCallbackHandler() {
                @Override
                public void processRow(ResultSet rs) throws SQLException{
                    Map<String, String> x = new HashMap<>();
                    x.put("name",rs.getString(1));
                    x.put("value",rs.getString(2));
                    item.add(x);
                    System.out.println(x);
                    while(rs.next())
                    {
                        Map<String, String> t = new HashMap<>();
                        t.put("name",rs.getString(1));
                        t.put("value",rs.getString(2));
                        System.out.println("tis "+t);
                        item.add(t);
                    }
                }
            });
            return item;
        }
        catch (Exception e)
        {
            System.out.println("exceptoin is "+e);
        }
        return  null;
    }



    public ArrayNode getTrends(String trend) {
        String Query="select year(date),"+ trend+", count(*) from opportunity group by year(date), "+ trend+" order by year(date); ";
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode outerList = mapper.createArrayNode();
        jdbcTemplate.query(Query, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                ArrayNode innerList = mapper.createArrayNode();
                ObjectNode innerNode = mapper.createObjectNode();
                ObjectNode outerNode = mapper.createObjectNode();
                innerNode.put("name", resultSet.getString(2));
                innerNode.put("value", resultSet.getString(3));
                innerList.add(innerNode);
                innerNode=mapper.createObjectNode();
                String currentString= resultSet.getString(1);
                while(resultSet.next()) {
                    if(currentString.equals(resultSet.getString(1))) {
                        innerNode.put("name", resultSet.getString(2));
                        innerNode.put("value", resultSet.getString(3));
                        innerList.add(innerNode);
                        System.out.println(currentString);
                        innerNode = mapper.createObjectNode();
                    }else {
                        outerNode.put("name", currentString);
                        outerNode.set("series", innerList);
                        outerList.add(outerNode);
                        outerNode=mapper.createObjectNode();
                        innerList=mapper.createArrayNode();
                        currentString = resultSet.getString(1);
                        innerNode.put("name", resultSet.getString(2));
                        innerNode.put("value", resultSet.getString(3));
                        innerList.add(innerNode);
                        innerNode=mapper.createObjectNode();
                    }
                }
                outerNode=mapper.createObjectNode();
                outerNode.put("name", currentString);
                outerNode.set("series", innerList);
                outerList.add(outerNode);
                System.out.println(outerList);
            }
        });
        return outerList;
    }
}