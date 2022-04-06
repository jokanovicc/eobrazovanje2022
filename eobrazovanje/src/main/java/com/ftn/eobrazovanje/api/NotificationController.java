package com.ftn.eobrazovanje.api;


import com.ftn.eobrazovanje.api.dto.NotificationDTO;
import com.ftn.eobrazovanje.exception.UserNonExistentException;
import com.ftn.eobrazovanje.model.User;
import com.ftn.eobrazovanje.service.NotificationService;
import com.ftn.eobrazovanje.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<NotificationDTO> sendNotification(@RequestBody NotificationDTO notification)
            throws URISyntaxException {
        if (notification.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        NotificationDTO result = notificationService.send(notification);
        return ResponseEntity
                .created(new URI("/api/notifications/" + result.getId()))
                .body(result);
    }

    @GetMapping
    public List<NotificationDTO> getOfStudent(Authentication authentication) {
        Optional<User> current = userService.getUser(authentication);
        if(current.isEmpty()) {
            throw new UserNonExistentException("Student doesn't exist");
        }
        return notificationService.getOfStudent(
                userService.getUser(authentication).get().getId()
        );
    }

}
