package com.mindex.challenge.data;

/**
 * This class is for Entity of type Reporting Structure.
 *
 * @Author: Shivangi Mahajan (sm9963@rit.edu)
 */

public class ReportingStructure {

    private Employee employee;
    private int numberOfReports;

    public ReportingStructure(){}

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }

    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }


}
