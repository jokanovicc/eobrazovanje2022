package com.ftn.eobrazovanje.service.impl;

import com.ftn.eobrazovanje.api.dto.NotificationDTO;
import com.ftn.eobrazovanje.api.dto.mapper.NotificationMapper;
import com.ftn.eobrazovanje.exception.UserNonExistentException;
import com.ftn.eobrazovanje.model.Notification;
import com.ftn.eobrazovanje.model.Performance;
import com.ftn.eobrazovanje.model.Teacher;
import com.ftn.eobrazovanje.model.User;
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
    public NotificationDTO create(NotificationDTO dto, Authentication authentication) {

        Optional<User> teacher = userService.getUser(authentication);
        if(teacher.isEmpty()) {
            throw new UserNonExistentException("User doesn't exist");
        }

        Notification notification = new Notification(
                new Performance(dto.getCoursePerformance().getId()),
                new Teacher(teacher.get().getId()),
                dto.getMessage()
        );
        Notification created = notificationRepository.save(notification);
        Notification found = notificationRepository.findById(created.getId()).get();

        return NotificationMapper.toDto(notificationRepository.findById(found.getId()).get());
    }

    @Override
    public List<NotificationDTO> getOfStudent(Long studentId) {
        return NotificationMapper.toDtoList(notificationRepository.getOfStudent(studentId));
    }
}
