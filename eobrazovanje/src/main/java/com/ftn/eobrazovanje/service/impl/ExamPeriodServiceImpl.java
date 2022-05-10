package com.ftn.eobrazovanje.service.impl;

import com.ftn.eobrazovanje.model.ExamPeriod;
import com.ftn.eobrazovanje.repository.ExamPeriodRepository;
import com.ftn.eobrazovanje.service.ExamPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamPeriodServiceImpl implements ExamPeriodService {

    @Autowired
    private ExamPeriodRepository examPeriodRepository;

    @Override
    public List<ExamPeriod> getActive() {
        List<ExamPeriod> all = examPeriodRepository.findAll();
        List<ExamPeriod> active = all.stream().filter(this::periodIsInRange)
                .collect(Collectors.toList());

        return active;
    }

    private boolean periodIsInRange(ExamPeriod period) {
        LocalDate today = LocalDate.now();
        LocalDate monthFromNow = today.plusDays(30);
        LocalDate monthBefore = today.minusDays(30);

        return period.getStartDate().isBefore(monthFromNow) &&
        period.getStartDate().isAfter(monthBefore);
    }


}
