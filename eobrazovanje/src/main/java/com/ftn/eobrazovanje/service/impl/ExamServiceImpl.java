package com.ftn.eobrazovanje.service.impl;

import com.ftn.eobrazovanje.api.dto.*;
import com.ftn.eobrazovanje.api.dto.mapper.ExamMapper;
import com.ftn.eobrazovanje.api.dto.mapper.ExamWithStudentInfoMapper;
import com.ftn.eobrazovanje.api.dto.mapper.PerformanceExamMapper;
import com.ftn.eobrazovanje.exception.BadRequestException;
import com.ftn.eobrazovanje.exception.ExamRegistrationFailedException;
import com.ftn.eobrazovanje.exception.NotFoundException;
import com.ftn.eobrazovanje.model.*;
import com.ftn.eobrazovanje.repository.*;
import com.ftn.eobrazovanje.service.CourseService;
import com.ftn.eobrazovanje.service.ExamService;
import com.ftn.eobrazovanje.service.PerformanceService;
import com.ftn.eobrazovanje.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private PerformanceRepository performanceRepository;

    @Autowired
    private ExamRegistrationRepository examRegistrationRepository;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private CourseService courseService;

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

        if(status == ExamStatus.PRELIMINARY) {
            return ExamMapper.toDtoList(examRepository.findPreliminaryByStudentId(studentId));
        }

        //istorija polaganja
        return ExamMapper.toDtoList(examRepository.findAllByStudent(studentId));
    }

    @Override
    public ExamDTO registerExam(
            Long performanceExamId,
            Long attendingId,
            Authentication authentication
    ) {
        User user = userService.getUser(authentication);
        Student student = studentRepository.findById(user.getId()).get();

        checkIfExamCanBeRegistered(attendingId, user.getId(), performanceExamId);

        PerformanceExam exam = performanceExamRepository.findById(performanceExamId).get();
        FinancialCard card = financialCardRepository.findByStudentId(user.getId()).get();
        Attending attending = attendingRepository.findById(attendingId).get();

        chargeStudentForExam(card, student);

        Exam registered = new Exam(0, 0, attending, 0, exam, "REGISTERED");
        examRepository.save(registered);

        ExamRegistration registration = new ExamRegistration(registered, student, exam.getExamPeriod());
        examRegistrationRepository.save(registration);

        return ExamMapper.toDto(registered);
    }

    @Override
    public List<ExamDTO> findExamByTeacher(Authentication authentication) {
        User user = userService.getUser(authentication);
        return ExamMapper.toDtoList(examRepository.findAllExamsByTeacher(user.getId()));
    }

    //profesoru ce na interfejsu biti prikazana lista EXAMA za svakog pojedinacnog studenta znaci nece biti
    //performance exam generalni, nego pojedinacna polaganja, tako da cu za svako polaganje imati direkt id pojedinacnog polaganja
    @Override
    public ExamDTO gradeStudent(
            Long examId,
            Long studentId,
            GradeStudentRequest studentGrade,
            Authentication authentication
    ) {
        Exam exam = examRepository.findById(examId).get();
        this.validatePoints(studentGrade.getPreExamDutyPoints(), studentGrade.getFinalExamPoints());
        int grade = calculateStudentGrade(studentGrade.getPreExamDutyPoints(), studentGrade.getFinalExamPoints());

        exam.setPreExamDutyPoints(studentGrade.getPreExamDutyPoints());
        exam.setFinalExamPoints(studentGrade.getFinalExamPoints());
        exam.setGrade(grade);
//        exam.setStatus(String.valueOf(calculateExamStatus(grade)));

        examRepository.save(exam);

        return ExamMapper.toDto(exam);
    }

    @Override
    public List<Exam> findExamsByPerformanceExam(Long id) {
        return examRepository.findExamsByPerformanceExam(id);
    }

    @Override
    public List<ExamWithStudentInfoResponse> getStudentsWhoRegisteredExam(Long performanceExamId) {
        return ExamWithStudentInfoMapper.toDtoList(
                examRepository.findAllRegisteredForPerformanceExam(performanceExamId)
        );
    }

    @Override
    public PerformanceExamResponse createExam(CreateExamRequest request) {
        Optional<Performance> performance = performanceRepository.findById(request.getPerformanceId());
        Optional<ExamPeriod> period = examPeriodRepository.findById(request.getExamPeriodId());
        if(performance.isEmpty() || period.isEmpty()) {
            throw new BadRequestException("Provided performance or period id not found");
        }
        PerformanceExam exam = new PerformanceExam(
                performance.get(),
                request.getDate(),
                period.get(),
                request.getClassroom(),
                ExamStatus.UNRATED.toString()
        );

        return PerformanceExamMapper.toDto(performanceExamRepository.save(exam));
    }

    @Override
    public List<PerformanceExamResponse> getExamsForRegistration(
            Authentication authentication,
            Long examPeriodId
    ) {
        User student = userService.getUser(authentication);
        if(examPeriodId == null) {
            return PerformanceExamMapper.toDtoList(performanceExamRepository.getByStudentId(student.getId()));
        }
        return PerformanceExamMapper.toDtoList(
                performanceExamRepository.getByStudentIdInExamPeriod(student.getId(), examPeriodId)
        );
    }

    @Override
    public List<PerformanceExamDTO> getExamPeriodsByTeacher(Authentication authentication) {
        User u = userService.getUser(authentication);
        return PerformanceExamDTO.convertToDtoList(performanceExamRepository.findExamPeriodByTeacher(u.getId()));
    }

    @Override
    public PerformanceExamResponse publishPreliminaryResults(Authentication authentication,Long examId,
                                          PerformanceExamDTO performanceExamDTO) throws BadRequestException {
            User teacher = userService.getUser(authentication);
            Course course = courseService.findByExamId(examId);
            PerformanceExam performanceExam = performanceExamRepository.findById(examId).orElse(null);
            List<Exam> exams = findExamsByPerformanceExam(examId);
            if(exams.isEmpty()){
                throw new BadRequestException("No students");
            }
            for(Exam exam : exams){
                if(exam.getGrade() == null){
                    throw new BadRequestException("Must grade student");
                }
                exam.setStatus(ExamStatus.PRELIMINARY.toString());
                examRepository.save(exam);

            }
            performanceExam.setStatus(ExamStatus.PRELIMINARY.toString());
            performanceExamRepository.save(performanceExam);
            sendResultsNotifications(teacher,course,examId);

            return PerformanceExamMapper.toDto(performanceExam);
    }

    @Override
    public PerformanceExamResponse finalResults(Long examId, PerformanceExamDTO performanceExamDTO)throws BadRequestException{
        PerformanceExam performanceExam = performanceExamRepository.findById(examId).orElse(null);
        List<Exam> exams = findExamsByPerformanceExam(examId);
        if(exams.isEmpty()){
            throw new BadRequestException("No students");
        }
        for(Exam exam : exams){
            if(exam.getStatus().equals(ExamStatus.REGISTERED.toString())){
                throw new BadRequestException("Must preliminary results first");
            }else{
                if(exam.getGrade() > 5) {
                    exam.setStatus(ExamStatus.PASSED.toString());
                }
                else if(exam.getGrade() < 6){
                    exam.setStatus(ExamStatus.FAILED.toString());
                }else{
                    throw new BadRequestException("Grade is not valid");
                }
                examRepository.save(exam);
            }

        }
        performanceExam.setStatus(ExamStatus.FINALLY.toString());
        performanceExamRepository.save(performanceExam);
        return PerformanceExamMapper.toDto(performanceExam);
    }

    @Override
    public void deregisterExam(Long examId) {
        ExamRegistration registration = examRegistrationRepository.findByExamId(examId);
        examRegistrationRepository.deleteById(registration.getId());
        examRepository.deleteById(examId);
        FinancialCard card = financialCardRepository.findByStudentId(registration.getStudent().getId()).get();
        Payment payment = new Payment(
                registration.getStudent(),
                (double) 300,
                "Odjava ispita",
                "/",
                LocalDate.now()
        );
        paymentRepository.save(payment);
        card.setBalance(card.getBalance() + 300);
        financialCardRepository.save(card);
    }

    @Transactional
    void chargeStudentForExam(FinancialCard card, Student student) {
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

    private void checkIfExamCanBeRegistered(Long attendingId, Long userId, Long performanceExamId) {

        Optional<Attending> attendingOptional = attendingRepository.findById(attendingId);
        Optional<FinancialCard> cardOptional = financialCardRepository.findByStudentId(userId);
        Optional<PerformanceExam> performanceExamOptional = performanceExamRepository.findById(performanceExamId);

        checkIfStudentInfoIsEmpty(attendingOptional, cardOptional, performanceExamOptional);

        FinancialCard card = cardOptional.get();
        PerformanceExam exam = performanceExamOptional.get();

        if(!examPeriodIsActive(exam.getExamPeriod())) {
            throw new ExamRegistrationFailedException("Selected exam period is currently inactive");
        }
        if(registeringTimePassed(exam)) {
            throw new ExamRegistrationFailedException(
                    "Registering time for this exam has expired. Last day for registration was "
                            + exam.getDate().minusDays(3)
            );
        }

        if(card.getBalance() < 300) {
            throw new ExamRegistrationFailedException("Insufficient funds");
        }

    }

    private void sendResultsNotifications(User teacher, Course course, Long examId){
        PerformanceExam exam = performanceExamRepository.getOne(examId);
        List<User> users = userService.findRegisteredToExamUser(examId);
        for(User user : users){
            emailService.sendEmail(user.getEmail(),"Preliminarni rezultati ispita",
                    "Poštovani, objavljeni su preliminarni rezultati iz predmeta " +
                            course.getName() + " \r\n" + "Ispitni rok: " + exam.getExamPeriod().getName() +
                            "\r\n" + "Datum polaganja: " + exam.getDate() + "\r\n" + "Nastavnik: " +
                            teacher.getName() + " " + teacher.getLastname() + "\r\n" + "\r\n" + "Rezultate možete pogledati " +
                            "na studentskom web servisu");
        }
    }

    private boolean registeringTimePassed(PerformanceExam exam) {
        LocalDate today = LocalDate.now();
        return today.isAfter(exam.getDate().minusDays(3));
    }

    private boolean examPeriodIsActive(ExamPeriod period) {
        LocalDate today = LocalDate.now();
        return dateIsInRange(today, period.getStartDate().minusDays(20), period.getEndDate());
    }

    private boolean dateIsInRange(LocalDate date, LocalDate start, LocalDate end) {
        return !(date.isBefore(start.minusDays(3)) || date.isAfter(end));
    }

    private void checkIfStudentInfoIsEmpty(
            Optional<Attending> attending,
            Optional<FinancialCard> card,
            Optional<PerformanceExam> exam
    ) {
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

    private int calculateStudentGrade(int preExamDutyPoints, int finalExamPoints) {
        int sum = preExamDutyPoints + finalExamPoints;
        if(preExamDutyPoints < 51) {
            return 5;
        }
        if(sum > 50 && sum < 61) {
            return 6;
        }
        if(sum > 60 && sum < 71) {
            return 7;
        }
        if(sum > 70 && sum < 81) {
            return 8;
        }
        if(sum > 80 && sum < 91) {
            return 9;
        }
        return 10;
    }

    private ExamStatus calculateExamStatus(int grade) {
        if(grade < 6) {
            return ExamStatus.FAILED;
        }
        return ExamStatus.PASSED;
    }

    private void validatePoints(int preExamDutyPoints, int finalExamPoints) {
        int sum = preExamDutyPoints + finalExamPoints;
        if(preExamDutyPoints > 100 || preExamDutyPoints < 0) {
            throw new NotFoundException("Points are invalid. Must be between 1 and 100");
        }
    }
}
