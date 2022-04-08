package com.ftn.eobrazovanje.api.dto.mapper;

import com.ftn.eobrazovanje.api.dto.UserDTO;
import com.ftn.eobrazovanje.api.dto.UserUpdateDTO;
import com.ftn.eobrazovanje.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {


    public static User toEntity(UserDTO dto){
        return new User(
                dto.getId(),
                dto.getName(),
                dto.getLastname(),
                dto.getJmbg(),
                dto.getAddress(),
                dto.getUsername(),
                dto.getGender()

        );
    }

    public static UserDTO toDto(User user){
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getJmbg(),
                user.getAddress(),
                user.getUsername(),
                user.getGender()
        );
    }


    public static List<UserDTO> toDtoList(List<User> users){
        return users.stream().map(user -> toDto(user)).collect(Collectors.toList());
    }


}
