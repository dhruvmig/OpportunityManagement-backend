package com.msau.opportunitymanagement.Model;


import com.msau.opportunitymanagement.Models.Opportunity;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OpportunityTest {

    @Mock
    Opportunity opportunity;

    @Test
    public void testGetterSetterId() {
        Opportunity x = new Opportunity();
        int id = 10;
        x.setId(id);
        assertEquals(id, x.getId());
    }

    @Test
    public void testGetterSetterEd() {
        Opportunity x = new Opportunity();
        String Ed = "Dhruv";
        x.setED(Ed);
        System.out.println("ed i s"+x.getED());
        assertEquals(Ed,x.getED());
    }

    @Test
    public void testGetterSetterSkills() {
        Opportunity x = new Opportunity();
        String skills = "java";
        x.setSkills(skills);
        assertEquals(skills,x.getSkills());
    }

    @Test
    public void testGetterSetterDate() {
        Opportunity x = new Opportunity();
        String date = "26-05-2021";
        x.setDate(date);
        assertEquals(date,x.getDate());
    }

    @Test
    public void testGetterSetterLocation() {
        Opportunity x = new Opportunity();
        String location = "Bangalore";
        x.setDate(location);
        assertEquals(location,x.getDate());
    }

}
