package com.ftn.eobrazovanje.api;

import com.ftn.eobrazovanje.api.dto.FinancialCardDTO;
import com.ftn.eobrazovanje.model.User;
import com.ftn.eobrazovanje.service.UserService;
import com.ftn.eobrazovanje.service.impl.FinancialCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/financialCards")
public class FinancialCardController {

    private final FinancialCardService financialCardService;

    private final UserService userService;

    public FinancialCardController(FinancialCardService financialCardService, UserService userService) {
        this.financialCardService = financialCardService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity getFinancialCard(Authentication authentication){
        User user = userService.getUser(authentication);
        FinancialCardDTO financialCardDTO = financialCardService.findByStudent(user.getId());
        if(financialCardDTO != null){
            return new ResponseEntity(financialCardDTO, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity getOneByStudentId(@PathVariable Long studentId){
        FinancialCardDTO financialCardDTO = financialCardService.findByStudent(studentId);
        if(financialCardDTO != null){
            return new ResponseEntity(financialCardDTO, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
