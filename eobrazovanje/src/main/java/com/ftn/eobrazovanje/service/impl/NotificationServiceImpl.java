package com.ftn.eobrazovanje.service.impl;

import com.ftn.eobrazovanje.api.dto.NotificationRequest;
import com.ftn.eobrazovanje.api.dto.NotificationResponse;
import com.ftn.eobrazovanje.api.dto.mapper.NotificationMapper;

import com.ftn.eobrazovanje.model.*;

import com.ftn.eobrazovanje.model.Notification;
import com.ftn.eobrazovanje.model.Performance;
import com.ftn.eobrazovanje.model.Teacher;
import com.ftn.eobrazovanje.model.User;

import com.ftn.eobrazovanje.repository.NotificationRepository;
import com.ftn.eobrazovanje.repository.TeacherRepository;
import com.ftn.eobrazovanje.service.NotificationService;
import com.ftn.eobrazovanje.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public NotificationResponse create(NotificationRequest request, Authentication authentication) {

       User teacher = userService.getUser(authentication);

        Notification notification = new Notification(
                new Performance(request.getPerformanceId()),
//                teacherRepository.findById(teacher.getId()).get(),
                new Teacher(teacher.getId()),
                request.getMessage()
        );
            Notification created = notificationRepository.save(notification);
        System.out.print(created.getTeacher().getUser());
        Notification test = notificationRepository.findById(created.getId()).get();
        return NotificationMapper.toDto(notificationRepository.findById(created.getId()).get());

    }

    @Override
    public List<NotificationResponse> getNotifications(Authentication authentication) {
        User current = userService.getUser(authentication);

        if(current.getRole() == UserRole.ADMIN) {
            return NotificationMapper.toDtoList(notificationRepository.findAll());
        }

        if(current.getRole() == UserRole.TEACHER) {
            return NotificationMapper.toDtoList(notificationRepository.getOfTeacher(current.getId()));
        }

        return NotificationMapper.toDtoList(notificationRepository.getOfStudent(current.getId()));
    }
}
