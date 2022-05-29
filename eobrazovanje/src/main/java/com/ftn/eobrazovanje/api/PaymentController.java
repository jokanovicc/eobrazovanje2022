package com.ftn.eobrazovanje.api;


import com.ftn.eobrazovanje.api.dto.PaymentAdminRequestDTO;
import com.ftn.eobrazovanje.api.dto.PaymentRespDTO;
import com.ftn.eobrazovanje.api.dto.ReqPaymentDTO;
import com.ftn.eobrazovanje.api.dto.RespPaymentDTO;
import com.ftn.eobrazovanje.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/students/{id}")
    public ResponseEntity getAllByStudentId(@PathVariable Long id, @RequestParam(defaultValue = "0") Integer page){
        PaymentRespDTO payments = paymentService.findAllByStudentId(id, page);

        return new ResponseEntity(payments, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createPayment(@RequestBody ReqPaymentDTO paymentDTO)
        throws URISyntaxException {
            if (paymentDTO.getId() != null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Long id  = paymentService.create(paymentDTO);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(id)
                    .toUri();

            return ResponseEntity.created(location).build();
    }

    @PostMapping("/students/{id}")
    public ResponseEntity createPaymentByAdmin(@PathVariable Long id, @Validated @RequestBody PaymentAdminRequestDTO paymentDTO){
        if(paymentDTO.getAmount().equals("")){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Long createdId  = paymentService.createByAdmin(paymentDTO, id);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{createdId}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
