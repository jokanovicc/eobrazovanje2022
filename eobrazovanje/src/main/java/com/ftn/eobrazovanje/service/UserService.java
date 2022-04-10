package com.ftn.eobrazovanje.service;

import com.ftn.eobrazovanje.api.dto.UserUpdateDTO;
import com.ftn.eobrazovanje.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

   User getUser(Authentication authentication);

   User findByUsername(String username);

   User findOneById(Long id);

   void update(User user, UserUpdateDTO userUpdateDTO);

   void update(User user);

   User create(User user);
}
