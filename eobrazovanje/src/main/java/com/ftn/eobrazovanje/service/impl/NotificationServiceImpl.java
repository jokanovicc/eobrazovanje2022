package com.ftn.eobrazovanje.service.impl;

import com.ftn.eobrazovanje.api.dto.NotificationRequest;
import com.ftn.eobrazovanje.api.dto.NotificationDTO;
import com.ftn.eobrazovanje.api.dto.NotificationResponse;
import com.ftn.eobrazovanje.api.dto.mapper.NotificationMapper;

import com.ftn.eobrazovanje.model.*;

import com.ftn.eobrazovanje.model.Notification;
import com.ftn.eobrazovanje.model.User;

import com.ftn.eobrazovanje.repository.NotificationRepository;
import com.ftn.eobrazovanje.repository.PerformanceRepository;
import com.ftn.eobrazovanje.repository.TeacherRepository;
import com.ftn.eobrazovanje.service.NotificationService;
import com.ftn.eobrazovanje.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private PerformanceRepository performanceRepository;

    @Override
    public NotificationDTO create(NotificationRequest request, Authentication authentication) {

       User teacher = userService.getUser(authentication);

        Notification notification = new Notification(
                performanceRepository.findById(request.getPerformanceId()).get(),
                teacherRepository.findById(teacher.getId()).get(),
                request.getMessage()
        );
            Notification created = notificationRepository.save(notification);

        return NotificationMapper.toDto(notification);

    }

    @Override
    public NotificationResponse getNotifications(Authentication authentication, int page) {

        Pageable pageable = PageRequest.of(page, 10);

        User current = userService.getUser(authentication);


        if(current.getRole() == UserRole.ADMIN) {
            Page<Notification> notifications = notificationRepository.findAll(pageable);
            return new NotificationResponse(NotificationMapper.toDtoList(notifications.getContent()), notifications.getTotalPages());
        }

        if(current.getRole() == UserRole.TEACHER) {
            Page<Notification> notifications = notificationRepository.getOfTeacher(current.getId(), pageable);
            return new NotificationResponse(NotificationMapper.toDtoList(notifications.getContent()), notifications.getTotalPages());
        }
        Page<Notification> notifications = notificationRepository.getOfStudent(current.getId(), pageable);
        return new NotificationResponse(NotificationMapper.toDtoList(notifications.getContent()), notifications.getTotalPages());
    }

    @Override
    public void delete(Long notificationId) {
        notificationRepository.deleteById(notificationId);
    }
}
