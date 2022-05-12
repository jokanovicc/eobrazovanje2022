package com.ftn.eobrazovanje.service;

import com.ftn.eobrazovanje.model.ExamPeriod;

import java.util.List;

public interface ExamPeriodService {
    List<ExamPeriod> getAll(boolean active);
}
