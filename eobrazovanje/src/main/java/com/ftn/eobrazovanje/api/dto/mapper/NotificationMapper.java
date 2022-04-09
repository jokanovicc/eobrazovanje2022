package com.ftn.eobrazovanje.api.dto.mapper;

import com.ftn.eobrazovanje.api.dto.NotificationResponse;
import com.ftn.eobrazovanje.model.Notification;

import java.util.List;
import java.util.stream.Collectors;

public class NotificationMapper {

    public static NotificationResponse toDto(Notification notification) {
        return new NotificationResponse(
                notification.getId(),
                CoursePerformanceMapper.toDto(notification.getPerformance()),
                TeacherMapper.toDto(notification.getTeacher()),
                notification.getMessage()
        );
    }

    public static List<NotificationResponse> toDtoList(List<Notification> notifications) {
        return notifications
                .stream()
                .map(notification -> toDto(notification))
                .collect(Collectors.toList());
    }
}
