package com.ftn.eobrazovanje.service;

import com.ftn.eobrazovanje.api.dto.NotificationDTO;
import com.ftn.eobrazovanje.model.Notification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {
    NotificationDTO send(NotificationDTO notification);

    List<NotificationDTO> getOfStudent(Long studentId);
}
