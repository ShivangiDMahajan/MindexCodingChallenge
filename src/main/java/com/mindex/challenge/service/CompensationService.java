package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

public interface CompensationService {
    Compensation get(String id);
    Compensation create(Compensation compensation);
}
