package com.ftn.eobrazovanje.service;


import com.ftn.eobrazovanje.api.dto.NotificationRequest;
import com.ftn.eobrazovanje.api.dto.NotificationDTO;

import com.ftn.eobrazovanje.api.dto.NotificationResponse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {

    NotificationDTO create(NotificationRequest notification, Authentication authentication);

    NotificationResponse getNotifications(Authentication authentication, int page);

    void delete(Long notificationId);
}
