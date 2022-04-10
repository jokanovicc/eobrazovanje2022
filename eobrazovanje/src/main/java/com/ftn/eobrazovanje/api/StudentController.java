package com.ftn.eobrazovanje.api;

import com.ftn.eobrazovanje.api.dto.FirstPasswordDTO;
import com.ftn.eobrazovanje.helper.CSVHelper;
import com.ftn.eobrazovanje.model.Student;
import com.ftn.eobrazovanje.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity uploadFile(@RequestParam("file")MultipartFile file){
        if(CSVHelper.hasCSVFormat(file)){
            try {
                studentService.createFromCSV(file);
                return new ResponseEntity(HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = "/firstPassword")
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
}
