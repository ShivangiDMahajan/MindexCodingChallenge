package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {
    private String getCompensationUrl;
    private String createCompensationUrl;

    @Autowired
    private CompensationService compensationService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup(){
        String prefixUrl = "http://localhost:"+port;
        getCompensationUrl = prefixUrl+"/compensation/employee/{id}";
        createCompensationUrl = prefixUrl+"/compensation";
    }

    @Test
    public void testCreateRead(){
        //Setup Test
        Employee testEmployee = new Employee();
        testEmployee.setEmployeeId("c0c2293d-16bd-4603-8e08-638a9d18b22c");
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");
        LocalDateTime effectiveDate = LocalDateTime.now();
        Compensation compensation = new Compensation();
        compensation.setEmployee(testEmployee);
        compensation.setSalary(1000);
        compensation.setEffectiveDate(effectiveDate.format(DateTimeFormatter.ISO_DATE_TIME));

        //Test for Create
        Compensation createdCompensation = restTemplate.postForEntity(createCompensationUrl,compensation,Compensation.class).getBody();
        assertCompensationEquivalence(createdCompensation,compensation);

        //Test for Read
        Compensation readCompensation = restTemplate.getForEntity(getCompensationUrl,Compensation.class,testEmployee.getEmployeeId()).getBody();
        assertCompensationEquivalence(readCompensation,compensation);

    }

    private static void assertCompensationEquivalence(Compensation expected,Compensation actual){
        assertEquals(expected.getEmployee().getEmployeeId(),actual.getEmployee().getEmployeeId());
        assertEquals(expected.getEffectiveDate(),actual.getEffectiveDate());
        assertEquals(expected.getSalary(),actual.getSalary());
    }
}
