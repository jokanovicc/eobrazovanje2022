package com.ftn.eobrazovanje.api;

import com.ftn.eobrazovanje.api.dto.FirstPasswordDTO;
import com.ftn.eobrazovanje.api.dto.SVFormDTO;
import com.ftn.eobrazovanje.api.dto.StudentDTO;
import com.ftn.eobrazovanje.helper.CSVHelper;
import com.ftn.eobrazovanje.model.Student;
import com.ftn.eobrazovanje.model.User;
import com.ftn.eobrazovanje.service.StudentService;
import com.ftn.eobrazovanje.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    private final UserService userService;

    public StudentController(StudentService studentService, UserService userService) {
        this.studentService = studentService;
        this.userService = userService;
    }

    @GetMapping("performance/{performanceId}")
    public ResponseEntity getByCourseId(@PathVariable Long performanceId){
        List<StudentDTO> studentDTOS = studentService.findByPerformanceId(performanceId);

        return new ResponseEntity(studentDTOS, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity uploadFile(@RequestParam("file")MultipartFile file){
        if(CSVHelper.hasCSVFormat(file)){
            try {
                studentService.createFromCSV(file);
                return new ResponseEntity(HttpStatus.CREATED);
            }catch (Exception e){
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/firstPassword")
    public ResponseEntity setFirstPassword(@RequestBody FirstPasswordDTO firstPasswordDTO){
        Student student = studentService.findOneByPasswordToken(firstPasswordDTO.getToken());
        if(student == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if(student.isFirstLogin() == false){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        studentService.setFirstPassword(student, firstPasswordDTO.getPassword());

        return new ResponseEntity(HttpStatus.OK);

    }

    @PostMapping("/svForm")
    public ResponseEntity setSVForm(Authentication authentication,  @RequestBody SVFormDTO formDTO){
        User user = userService.getUser(authentication);
        Student student = studentService.findByUserId(user.getId());
        if(student.isCompletedSVForm()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        studentService.setSVForm(formDTO, user, student);

        return new ResponseEntity(HttpStatus.OK);
    }
}
