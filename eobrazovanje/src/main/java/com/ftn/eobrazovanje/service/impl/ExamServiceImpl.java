package com.ftn.eobrazovanje.service.impl;

import com.ftn.eobrazovanje.api.dto.ExamDTO;
import com.ftn.eobrazovanje.api.dto.mapper.ExamMapper;
import com.ftn.eobrazovanje.exception.ExamRegistrationFailedException;
import com.ftn.eobrazovanje.exception.NotFoundException;
import com.ftn.eobrazovanje.model.*;
import com.ftn.eobrazovanje.repository.*;
import com.ftn.eobrazovanje.service.ExamService;
import com.ftn.eobrazovanje.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private UserService userService;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ExamPeriodRepository examPeriodRepository;

    @Autowired
    private FinancialCardRepository financialCardRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AttendingRepository attendingRepository;

    @Autowired
    private PerformanceExamRepository performanceExamRepository;

    @Autowired
    private ExamRegistrationRepository examRegistrationRepository;

    public List<ExamDTO> getExamsForStudent(Authentication authentication, String examStatus) {
        User current = userService.getUser(authentication);

        Long studentId = current.getId();

        //polozeni predmeti
        ExamStatus status = ExamStatus.valueOf(examStatus);
        if(status == ExamStatus.PASSED) {
            return ExamMapper.toDtoList(examRepository.findPassedByStudentId(studentId));
        }

        //prijavljeni ispiti
        if(status == ExamStatus.REGISTERED) {
            return ExamMapper.toDtoList(examRepository.findRegisteredByStudentId(studentId));
        }

        if(status == ExamStatus.FAILED) {
            return ExamMapper.toDtoList(examRepository.findFailedByStudentId(studentId));
        }

        //istorija polaganja
        return ExamMapper.toDtoList(examRepository.findAllByStudentId(studentId));
    }

    @Override
    public ExamDTO registerExam(
            Long performanceExamId,
            Long attendingId,
            Long periodId,
            Authentication authentication
    ) {
        User user = userService.getUser(authentication);
        Student student = studentRepository.findById(user.getId()).get();

        checkIfExamCanBeRegistered(periodId, attendingId, user.getId(), performanceExamId);

        ExamPeriod period = examPeriodRepository.findById(periodId).get();
        PerformanceExam exam = performanceExamRepository.findById(performanceExamId).get();
        FinancialCard card = financialCardRepository.findByStudentId(user.getId()).get();
        Attending attending = attendingRepository.findById(attendingId).get();

        chargeStudentForExam(card, student);

        Exam registered = new Exam(0, 0, attending, 0, exam, "REGISTERED");
        examRepository.save(registered);

        ExamRegistration registration = new ExamRegistration(registered, student, period);
        examRegistrationRepository.save(registration);

        return ExamMapper.toDto(registered);
    }


    @Transactional
    private void chargeStudentForExam(FinancialCard card, Student student) {
        Payment payment = new Payment(
                student,
                (double) -300,
                "Prijava ispita",
                "/",
                LocalDate.now()
        );
        paymentRepository.save(payment);
        card.setBalance(card.getBalance() - 300);
        financialCardRepository.save(card);
    }

    private void checkIfExamCanBeRegistered(Long periodId, Long attendingId, Long userId, Long performanceExamId) {

        Optional<ExamPeriod> periodOptional = examPeriodRepository.findById(periodId);
        Optional<Attending> attendingOptional = attendingRepository.findById(attendingId);
        Optional<FinancialCard> cardOptional = financialCardRepository.findByStudentId(userId);
        Optional<PerformanceExam> performanceExamOptional = performanceExamRepository.findById(performanceExamId);

        checkIfStudentInfoIsEmpty(periodOptional, attendingOptional, cardOptional, performanceExamOptional);

        ExamPeriod period = periodOptional.get();
        FinancialCard card = cardOptional.get();
        PerformanceExam exam = performanceExamOptional.get();

        if(!examPeriodIsActive(period)) {
            throw new ExamRegistrationFailedException("Selected exam period is currently inactive");
        }
        if(registeringTimePassed(exam)) {
            throw new ExamRegistrationFailedException(
                    "Registering time for this exam has expired. Last day for registration was "
                            + exam.getDate().minusDays(3)
            );
        }

        //dodati neku logiku koliko ce koji ispit da kosta, po rokovima ili po koji se put prijavljuje
        if(card.getBalance() < 300) {
            throw new ExamRegistrationFailedException("Insufficient funds");
        }

    }

    private boolean registeringTimePassed(PerformanceExam exam) {
        LocalDate today = LocalDate.now();
        return today.isAfter(exam.getDate().minusDays(3));
    }

    private boolean examPeriodIsActive(ExamPeriod period) {
        LocalDate today = LocalDate.now();
        return dateIsInRange(today, period.getStartDate(), period.getEndDate());
    }

    private boolean dateIsInRange(LocalDate date, LocalDate start, LocalDate end) {
        return !(date.isBefore(start.minusDays(3)) || date.isAfter(end));
    }

    private void checkIfStudentInfoIsEmpty(
            Optional<ExamPeriod> period,
            Optional<Attending> attending,
            Optional<FinancialCard> card,
            Optional<PerformanceExam> exam
    ) {
        if(period.isEmpty()) {
            throw new NotFoundException("Exam period not found");
        }
        if(attending.isEmpty()) {
            throw new NotFoundException("Exam not found");
        }
        if(card.isEmpty()) {
            throw new NotFoundException("Financial card for student not found");
        }
        if(exam.isEmpty()) {
            throw new NotFoundException("Exam not found");
        }
    }
}
