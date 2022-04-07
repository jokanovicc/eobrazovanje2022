package com.ftn.eobrazovanje.service;

import com.ftn.eobrazovanje.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

   Optional<User> getUser(Authentication authentication);

   User findByUsername(String username);
}
