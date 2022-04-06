package com.ftn.eobrazovanje.api.dto.mapper;

import com.ftn.eobrazovanje.api.dto.TeacherDTO;
import com.ftn.eobrazovanje.model.Role;
import com.ftn.eobrazovanje.model.Teacher;
import com.ftn.eobrazovanje.model.User;

public class TeacherMapper {
    public static TeacherDTO toDto(Teacher teacher) {
        return new TeacherDTO(
                teacher.getId(),
                teacher.getUser().getName(),
                teacher.getUser().getJmbg(),
                teacher.getUser().getAddress(),
                teacher.getUser().getUsername(),
                teacher.getUser().getGender()
        );
    }

    public static Teacher toEntity(TeacherDTO dto) {
        User user = new User(
                dto.getId(),
                dto.getName(),
                dto.getJmbg(),
                dto.getAddress(),
                dto.getUsername(),
                new Role("teacher"),
                dto.getGender()
        );

        return new Teacher(dto.getId(), user);
    }
}
