package com.ftn.eobrazovanje.api;


import com.ftn.eobrazovanje.api.dto.NotificationRequest;
import com.ftn.eobrazovanje.api.dto.NotificationDTO;
import com.ftn.eobrazovanje.api.dto.NotificationResponse;
import com.ftn.eobrazovanje.service.NotificationService;
import com.ftn.eobrazovanje.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
@Validated
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping
    public ResponseEntity<NotificationDTO> sendNotification(
            @RequestBody NotificationRequest notification,
            Authentication authentication
            )
            throws URISyntaxException {

        NotificationDTO result = notificationService.create(notification, authentication);

        return ResponseEntity
                .created(new URI("/api/notifications/" + result.getId()))
                .body(result);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'STUDENT')")
    @GetMapping
    public NotificationResponse getNotifications(
            Authentication authentication,
            @RequestParam(required = false, defaultValue = "0") int page) {
        return notificationService.getNotifications(authentication, page);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    @DeleteMapping("/{notificationId}")
    public void delete(@PathVariable Long notificationId) {
        notificationService.delete(notificationId);
    }

}
