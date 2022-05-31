package com.ftn.eobrazovanje.service.impl;

import com.ftn.eobrazovanje.api.dto.UserUpdateDTO;

import com.ftn.eobrazovanje.exception.UserNonExistentException;

import com.ftn.eobrazovanje.model.User;
import com.ftn.eobrazovanje.repository.UserRepository;
import com.ftn.eobrazovanje.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User getUser(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        String username = userPrincipal.getUsername();

        Optional<User> user = userRepository.findFirstByUsername(username);

        if(user.isEmpty()) {
            throw new UserNonExistentException("User doesn't exist");
        }
        return user.get();

    }

    @Override
    public User findByUsername(String username) {
        Optional<User> user = userRepository.findFirstByUsername(username);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    @Override
    public User findOneById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findOneByEmail(String email) {
        return userRepository.findFirstByEmail(email).orElse(null);
    }

    @Override
    public User update(User user, UserUpdateDTO userUpdateDTO) {
        user.setAddress(userUpdateDTO.getAddress());
        user.setUsername(userUpdateDTO.getUsername());
        user.setLastname(userUpdateDTO.getLastname());
        user.setName(userUpdateDTO.getName());
        return userRepository.save(user);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public User create(User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public List<User> findRegisteredToExamUser(Long examId) {
        return userRepository.findAllRegisteredToExamStudents(examId);
    }

    @Override
    public User findByJmbg(String jmbg) {
        return userRepository.findByJmbg(jmbg);
    }
}
