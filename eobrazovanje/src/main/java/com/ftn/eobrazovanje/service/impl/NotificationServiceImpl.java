package com.ftn.eobrazovanje.service.impl;

import com.ftn.eobrazovanje.api.dto.NotificationDTO;
import com.ftn.eobrazovanje.api.dto.mapper.NotificationMapper;
import com.ftn.eobrazovanje.model.Notification;
import com.ftn.eobrazovanje.repository.NotificationRepository;
import com.ftn.eobrazovanje.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public NotificationDTO send(NotificationDTO dto) {
        Notification notification = NotificationMapper.toEntity(dto);
        return NotificationMapper.toDto(notificationRepository.save(notification));
    }

    @Override
    public List<NotificationDTO> getOfStudent(Long studentId) {
        return NotificationMapper.toDtoList(notificationRepository.getOfStudent(studentId));
    }
}
