package com.msau.opportunitymanagement.DAO;

//import jdk.internal.net.http.common.Pair;
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

    private JdbcTemplate jdbcTemplate;
    public TrendsDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Map<String, String>> getSkills(String trend) {
        System.out.println("trend is "+trend);
        String sql="SELECT "+trend+", count(*) FROM opportunity GROUP BY "+trend+"; ";
        System.out.println(sql);
        List<Map<String,String>> item = new ArrayList<>();
         jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                while(rs.next())
                {
                    Map<String, String> t = new HashMap<>();
                    t.put("name",rs.getString(1));
                    t.put("value",rs.getString(2));
                    item.add(t);
                }
//                System.out.println("result set is "+rs);
            }
        });
        return item;
    }
}
