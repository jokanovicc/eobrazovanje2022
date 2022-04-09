package com.ftn.eobrazovanje.service;

<<<<<<< HEAD
import com.ftn.eobrazovanje.api.dto.NotificationRequest;
import com.ftn.eobrazovanje.api.dto.NotificationResponse;
=======
import com.ftn.eobrazovanje.api.dto.NotificationDTO;
import com.ftn.eobrazovanje.model.Notification;
import org.springframework.data.jpa.repository.Query;
>>>>>>> payments-api
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {

    NotificationResponse create(NotificationRequest notification, Authentication authentication);

    List<NotificationResponse> getNotifications(Authentication authentication);
}
