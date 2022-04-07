package com.ftn.eobrazovanje.service.impl;

import com.ftn.eobrazovanje.api.dto.ExamDTO;
import com.ftn.eobrazovanje.api.dto.mapper.ExamMapper;
import com.ftn.eobrazovanje.exception.UserNonExistentException;
import com.ftn.eobrazovanje.model.ExamStatus;
import com.ftn.eobrazovanje.model.User;
import com.ftn.eobrazovanje.repository.ExamRepository;
import com.ftn.eobrazovanje.service.ExamService;
import com.ftn.eobrazovanje.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private UserService userService;

    @Autowired
    private ExamRepository examRepository;

    public List<ExamDTO> getExamsForStudent(Authentication authentication, String examStatus) {
        Optional<User> current = userService.getUser(authentication);
        if(current.isEmpty()) {
            throw new UserNonExistentException("User doesn't exist");
        }

        Long studentId = current.get().getId();

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
}
