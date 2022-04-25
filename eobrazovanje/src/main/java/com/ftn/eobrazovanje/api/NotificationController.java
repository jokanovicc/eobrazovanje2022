package com.ftn.eobrazovanje.api;


import com.ftn.eobrazovanje.api.dto.NotificationRequest;
import com.ftn.eobrazovanje.api.dto.NotificationResponse;
import com.ftn.eobrazovanje.exception.UserNonExistentException;
import com.ftn.eobrazovanje.model.User;
import com.ftn.eobrazovanje.service.NotificationService;
import com.ftn.eobrazovanje.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<NotificationResponse> sendNotification(
            @RequestBody NotificationRequest notification,
            Authentication authentication
            )
            throws URISyntaxException {

        NotificationResponse result = notificationService.create(notification, authentication);

        return ResponseEntity
                .created(new URI("/api/notifications/" + result.getId()))
                .body(result);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'STUDENT')")
    @GetMapping
    public List<NotificationResponse> getNotifications(Authentication authentication) {
        return notificationService.getNotifications(authentication);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    @DeleteMapping("/{notificationId}")
    public void delete(@PathVariable Long notificationId) {
        notificationService.delete(notificationId);
    }

}
