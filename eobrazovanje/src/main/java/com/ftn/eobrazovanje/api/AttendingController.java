package com.ftn.eobrazovanje.api;

import com.ftn.eobrazovanje.api.dto.StudentsAttendingPerformanceDTO;
import com.ftn.eobrazovanje.exception.StudentNonExistentException;
import com.ftn.eobrazovanje.service.AttendingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendings")
public class AttendingController {


    private final AttendingService attendingService;


    public AttendingController(AttendingService attendingService) {
        this.attendingService = attendingService;
    }

    @PostMapping("performance/{performanceId}")
    public ResponseEntity addStudentsToCourse(@RequestBody StudentsAttendingPerformanceDTO dto,
                                              @PathVariable Long performanceId){

           List<String> wrongIndexes = attendingService.create(dto, performanceId);
           if(wrongIndexes.isEmpty()){
               return new ResponseEntity(HttpStatus.CREATED);
           }
            return new ResponseEntity("Students don't exist" + wrongIndexes,HttpStatus.CREATED);


    }

    @DeleteMapping("students/{studenId}/performance/{performanceId}")
    public ResponseEntity deleteByStudentAndPerformance(@PathVariable Long studenId, @PathVariable Long performanceId){
        boolean isDeleted = attendingService.delete(studenId, performanceId);
        if(isDeleted){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
