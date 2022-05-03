package com.ftn.eobrazovanje.api.dto.mapper;

import com.ftn.eobrazovanje.api.dto.AttendingDTO;
import com.ftn.eobrazovanje.model.Attending;

public class AttendingMapper {

    public static AttendingDTO toDTO(Attending attending) {
        return new AttendingDTO(attending.getId(),
                CoursePerformanceMapper.toDto(attending.getPerformance()),
                StudentMapper.toDto(attending.getStudent()));
    }
}
