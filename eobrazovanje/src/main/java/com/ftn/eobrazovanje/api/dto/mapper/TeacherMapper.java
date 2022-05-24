package com.ftn.eobrazovanje.api.dto.mapper;

import com.ftn.eobrazovanje.api.dto.TeacherDTO;
import com.ftn.eobrazovanje.model.Role;
import com.ftn.eobrazovanje.model.Teacher;
import com.ftn.eobrazovanje.model.User;
import com.ftn.eobrazovanje.model.UserRole;

import java.util.List;
import java.util.stream.Collectors;

public class TeacherMapper {

    public static TeacherDTO toDto(Teacher teacher) {
        if(teacher.getUser() == null) {
            return new TeacherDTO(teacher.getId());
        }
        return new TeacherDTO(
                teacher.getId(),
                teacher.getUser().getName(),
                teacher.getUser().getLastname(),
                teacher.getUser().getJmbg(),
                teacher.getUser().getAddress(),
                teacher.getUser().getUsername(),
                teacher.getUser().getGender(),
                teacher.getUser().getPassword(),
                teacher.getUser().getEmail()
        );
    }

    public static List<TeacherDTO> toDTOList(List<Teacher> teachers){
        return teachers
                .stream()
                .map(teacher -> TeacherMapper.toDto(teacher))
                .collect(Collectors.toList());
    }

    public static Teacher toEntity(TeacherDTO dto) {
        User user = new User(
                dto.getId(),
                dto.getName(),
                dto.getLastname(),
                dto.getJmbg(),
                dto.getAddress(),
                dto.getUsername(),
                UserRole.TEACHER,
                dto.getGender(),
                dto.getPassword(),
                dto.getEmail()
        );

        return new Teacher(dto.getId(), user);
    }
}
