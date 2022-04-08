package com.ftn.eobrazovanje.api.dto.mapper;

import com.ftn.eobrazovanje.api.dto.NotificationDTO;
import com.ftn.eobrazovanje.model.Notification;

import java.util.List;
import java.util.stream.Collectors;

public class NotificationMapper {
    public static Notification toEntity(NotificationDTO dto) {
        return new Notification(
                dto.getId(),
                CoursePerformanceMapper.toEntity(dto.getCoursePerformance()),
                TeacherMapper.toEntity(dto.getTeacher()),
                dto.getMessage()
        );
    }

    public static NotificationDTO toDto(Notification notification) {
        return new NotificationDTO(
                notification.getId(),
                CoursePerformanceMapper.toDto(notification.getPerformance()),
                TeacherMapper.toDto(notification.getTeacher()),
                notification.getMessage()
        );
    }

    public static List<NotificationDTO> toDtoList(List<Notification> notifications) {
        return notifications
                .stream()
                .map(notification -> toDto(notification))
                .collect(Collectors.toList());
    }
}
