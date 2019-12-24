package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * This is a representation of repository for Compensation.
 *
 * @Author: Shivangi Mahajan (sm9963@rit.edu)
 */

@Repository
public interface CompensationRepository extends MongoRepository<Compensation,String> {

    @Query("{'employee.employeeId': ? 0}")
    Compensation findByEmployeeId(String employeeId);
}
