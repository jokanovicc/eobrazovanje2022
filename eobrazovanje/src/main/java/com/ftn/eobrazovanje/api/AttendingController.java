package com.ftn.eobrazovanje.api;

import com.ftn.eobrazovanje.api.dto.AttendingBulkResponseDTO;
import com.ftn.eobrazovanje.api.dto.AttendingDTO;
import com.ftn.eobrazovanje.api.dto.AttendingPaginableDTO;
import com.ftn.eobrazovanje.api.dto.StudentsAttendingPerformanceDTO;
import com.ftn.eobrazovanje.exception.BadRequestException;
import com.ftn.eobrazovanje.exception.StudentNonExistentException;
import com.ftn.eobrazovanje.helper.CSVHelper;
import com.ftn.eobrazovanje.service.AttendingService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/attendings")
public class AttendingController {


    private final AttendingService attendingService;


    public AttendingController(AttendingService attendingService) {
        this.attendingService = attendingService;
    }

    @PostMapping("/performance/{performanceId}")
    public ResponseEntity addStudentsToCourse(@RequestBody StudentsAttendingPerformanceDTO dto,
                                              @PathVariable Long performanceId){

        try {
            List<String> wrongIndexes = attendingService.create(dto, performanceId);
            if (wrongIndexes.isEmpty()) {
                return new ResponseEntity(HttpStatus.CREATED);
            }
            return new ResponseEntity("Students don't exist" + wrongIndexes, HttpStatus.CREATED);
        }catch (BadRequestException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }


    }

    @DeleteMapping("students/{studenId}/performance/{performanceId}")
    public ResponseEntity deleteByStudentAndPerformance(@PathVariable Long studenId, @PathVariable Long performanceId){
        boolean isDeleted = attendingService.delete(studenId, performanceId);
        if(isDeleted){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/performances/{performanceId}/latest")
    public ResponseEntity<AttendingDTO> getLatestAttendingOfCourseForStudent(
            Authentication authentication,
            @PathVariable Long performanceId
    ) {
        return ResponseEntity
                .ok()
                .body(attendingService.findLatestOfCourseForStudent(authentication, performanceId));
    }

    @GetMapping("/performances/{performanceId}")
    public ResponseEntity getAttendingByPerformance(@PathVariable Long performanceId, @RequestParam(defaultValue = "0") Integer page){
        AttendingPaginableDTO dto = attendingService.getStudentsOfCourse(performanceId, page);
        return new ResponseEntity(dto, HttpStatus.OK);
    }


    @PostMapping("/perfromances/{performanceId}/bulkStudents")
    public ResponseEntity uploadStudentsPerformanceFile(@RequestParam("file") MultipartFile file, @PathVariable Long performanceId){
        if(CSVHelper.hasCSVFormat(file)){
            try {
                AttendingBulkResponseDTO response = attendingService.addStudentsFromFile(file, performanceId);
                return new ResponseEntity(response, HttpStatus.CREATED);
            }catch (Exception e){
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
