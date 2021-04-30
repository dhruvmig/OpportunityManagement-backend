package com.msau.opportunitymanagement.DAO;

//import jdk.internal.net.http.common.Pair;
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
         jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException{
                System.out.println("class is "+rs.getClass()+"\t hello"+rs.toString());
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

    @Override
    public void getTrends() {
        String query = "select year(date), skills, count(*) from opportunity group by year(date), skills order by year(date);";
//        Map<String,List<Map<String,String>>> item = new HashMap<>();

//        jdbcTemplate.query(sql, new RowCallbackHandler() {
//
//            @Override
//            public void processRow(ResultSet rs) throws SQLException {
//                System.out.println("here the result is "+rs);
//                Map<String,String> x = new HashMap<>();
//                List<Map<String,String>> l = new ArrayList<>();
//                x.put("name",rs.getString(1));
//                x.put("value",rs.getString(3));
//                l.add(x);
//
//                item.put(rs.getString(2),l);
//                System.out.println("x here is "+item);
//            }
//
//        });
        List <String> years=new ArrayList<>();
        List <String> columns = new ArrayList<>();
        long[][] data= new long[5][5];
        jdbcTemplate.query(query,new RowCallbackHandler() {

            @Override
            public void processRow(ResultSet rs) throws SQLException {
                String year = rs.getString(1);
                String column = rs.getString(2);
                if(!years.contains(year)) {
                    years.add(year);
                }
                if(!columns.contains(column)) {
                    columns.add(column);
                }
                data[columns.indexOf(column)][years.indexOf(year)] = rs.getInt(3);
            }
    });
//        System.out.println("data i s"+data);
        for(int i=0;i<data.length;i++)
        {
            for(int j=0;j<data[i].length;j++)
            {
                System.out.print(data[i][j]);
            }
            System.out.println();
        }
}

}


