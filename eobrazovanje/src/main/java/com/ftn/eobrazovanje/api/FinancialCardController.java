package com.ftn.eobrazovanje.api;

import com.ftn.eobrazovanje.api.dto.FinancialCardDTO;
import com.ftn.eobrazovanje.service.impl.FinancialCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/financialCards")
public class FinancialCardController {

    private final FinancialCardService financialCardService;

    public FinancialCardController(FinancialCardService financialCardService) {
        this.financialCardService = financialCardService;
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
