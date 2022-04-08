package com.ftn.eobrazovanje.service;

import com.ftn.eobrazovanje.api.dto.NotificationDTO;
import com.ftn.eobrazovanje.model.Notification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {
    NotificationDTO create(NotificationDTO notification, Authentication authentication);

    List<NotificationDTO> getOfStudent(Long studentId);
}
