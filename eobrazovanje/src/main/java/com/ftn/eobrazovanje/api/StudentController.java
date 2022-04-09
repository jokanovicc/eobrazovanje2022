package com.ftn.eobrazovanje.api;

import com.ftn.eobrazovanje.helper.CSVHelper;
import com.ftn.eobrazovanje.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
}
