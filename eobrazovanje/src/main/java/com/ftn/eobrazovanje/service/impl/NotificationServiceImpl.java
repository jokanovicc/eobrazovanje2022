package com.ftn.eobrazovanje.service.impl;

import com.ftn.eobrazovanje.api.dto.NotificationRequest;
import com.ftn.eobrazovanje.api.dto.NotificationResponse;
import com.ftn.eobrazovanje.api.dto.mapper.NotificationMapper;
<<<<<<< HEAD
import com.ftn.eobrazovanje.model.*;
=======
import com.ftn.eobrazovanje.exception.UserNonExistentException;
import com.ftn.eobrazovanje.model.Notification;
import com.ftn.eobrazovanje.model.Performance;
import com.ftn.eobrazovanje.model.Teacher;
import com.ftn.eobrazovanje.model.User;
>>>>>>> payments-api
import com.ftn.eobrazovanje.repository.NotificationRepository;
import com.ftn.eobrazovanje.service.NotificationService;
import com.ftn.eobrazovanje.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserService userService;

    @Override
    public NotificationResponse create(NotificationRequest request, Authentication authentication) {

       User teacher = userService.getUser(authentication);

        Notification notification = new Notification(
                new Performance(request.getPerformanceId()),
                new Teacher(teacher.getId()),
                request.getMessage()
        );
        Notification created = notificationRepository.save(notification);

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
