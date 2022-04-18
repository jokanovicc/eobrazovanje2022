package com.ftn.eobrazovanje.api;

import com.ftn.eobrazovanje.api.dto.LoginDTO;
import com.ftn.eobrazovanje.api.dto.TeacherDTO;
import com.ftn.eobrazovanje.api.dto.UserDTO;
import com.ftn.eobrazovanje.api.dto.UserUpdateDTO;
import com.ftn.eobrazovanje.api.dto.mapper.TeacherMapper;
import com.ftn.eobrazovanje.api.dto.mapper.UserMapper;
import com.ftn.eobrazovanje.exception.UserNonExistentException;
import com.ftn.eobrazovanje.model.Teacher;
import com.ftn.eobrazovanje.model.User;
import com.ftn.eobrazovanje.security.TokenUtils;
import com.ftn.eobrazovanje.service.TeacherService;
import com.ftn.eobrazovanje.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TeacherService teacherService;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO userDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
            return ResponseEntity.ok(tokenUtils.generateToken(userDetails));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping
    public UserDTO getMyInfo(Authentication authentication){
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        User user = userService.findByUsername(userPrincipal.getUsername());
        if(user == null) {
            throw new UserNonExistentException("User doesn't exist");
        }
        return UserMapper.toDto(user);

    }

    @PutMapping
    public void updateInfo(Authentication authentication, @RequestBody UserUpdateDTO userUpdateDTO){
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        User user = userService.findByUsername(userPrincipal.getUsername());
        if(user == null) {
            throw new UserNonExistentException("User doesn't exist");
        }
        userService.update(user, userUpdateDTO);


    }


    @PostMapping
    public void createTeacher(@RequestBody TeacherDTO teacherDTO){
        Teacher teacher = TeacherMapper.toEntity(teacherDTO);
        teacher.getUser().setPassword(passwordEncoder.encode(teacherDTO.getPassword()));
        teacherService.createTeacher(teacher);


    }

    //param je /teachers?pageNo=1
    @GetMapping("/teachers")
    public List<TeacherDTO> getAllTeacher(@RequestParam(defaultValue = "0") Integer pageNo){
        List<Teacher> list = teacherService.getAllTeachers(pageNo);
        List<TeacherDTO> teacherDTOS = TeacherMapper.toDTOList(list);

        return teacherDTOS;

    }


}
