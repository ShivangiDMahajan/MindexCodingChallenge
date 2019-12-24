package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is a rest controller for Reporting Structure.
 *
 * @Author: Shivangi Mahajan (sm9963@rit.edu)
 */

@RestController
public class ReportingStructureController {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

    @Autowired
    ReportingStructureService reportingStructureService;

    @GetMapping("/reportingStructure/{id}")
    public ReportingStructure read(@PathVariable String id){
        LOG.debug("Received reporting structure create request for id [{}]", id);
        return reportingStructureService.get(id);
    }
}
