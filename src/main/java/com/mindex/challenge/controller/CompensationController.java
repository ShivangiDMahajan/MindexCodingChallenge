package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * This class is a rest controller for Compensation.
 *
 * @Author: Shivangi Mahajan (sm9963@rit.edu)
 */

@RestController
public class CompensationController {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    @Autowired
    CompensationService compensationService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/compensation/employee/{id}")
    public Compensation read(@PathVariable String id){
        LOG.debug("Received compensation read request for [{}]", id);
        return compensationService.get(id);
    }

    @PostMapping("/compensation")
    public Compensation create(@RequestBody Compensation compensation){
        LOG.debug("Received compensation create request for [{}]", compensation);
        return compensationService.create(compensation);
    }
}
