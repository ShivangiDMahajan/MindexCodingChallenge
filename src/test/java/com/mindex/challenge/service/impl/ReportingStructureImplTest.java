package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.CompensationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureImplTest {
    private String getReportingStructure;

    @Autowired
    private CompensationService compensationService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup(){
        String prefixUrl = "http://localhost:"+port;
        getReportingStructure = prefixUrl+"/reportingStructure/{id}";
    }

    @Test
    public void readTest(){
        //Setup Test
        Employee testEmployee = new Employee();
        testEmployee.setEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");

        // test for Read
        ReportingStructure reportingStructure = restTemplate.getForEntity(getReportingStructure,ReportingStructure.class,testEmployee.getEmployeeId()).getBody();
        assertEquals(reportingStructure.getNumberOfReports(),4);
    }
}
