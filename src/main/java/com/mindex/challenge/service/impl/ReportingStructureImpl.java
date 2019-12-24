package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class is an implementation of ReportingStructureService.
 *
 * @Author: Shivangi Mahajan (sm9963@rit.edu)
 */

@Service
public class ReportingStructureImpl implements ReportingStructureService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public ReportingStructure get(String id) {
        Employee employee = employeeRepository.findByEmployeeId(id);
        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setEmployee(employee);
        int noOfReports = findAllReports(employee);
        reportingStructure.setNumberOfReports(noOfReports);
        return reportingStructure;
    }

    private int findAllReports(Employee employee) {
        int numberOfReportsForCurrentEmployee;
        //Fetching the employee object from database as the directReports object does not contain information of employee
        List<Employee> directReports = employeeRepository
                .findByEmployeeId(employee.getEmployeeId())
                .getDirectReports();

        if (directReports == null || directReports.size() == 0) {
            return 0;
        }

        numberOfReportsForCurrentEmployee = directReports.size();
        for (Employee e : directReports) {
            numberOfReportsForCurrentEmployee += findAllReports(e);
        }
        return numberOfReportsForCurrentEmployee;
    }
}
