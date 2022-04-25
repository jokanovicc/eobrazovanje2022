package com.ftn.eobrazovanje.service;


import com.ftn.eobrazovanje.api.dto.NotificationRequest;
import com.ftn.eobrazovanje.api.dto.NotificationResponse;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {

    NotificationResponse create(NotificationRequest notification, Authentication authentication);

    List<NotificationResponse> getNotifications(Authentication authentication);

    void delete(Long notificationId);
}
