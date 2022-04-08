package com.ftn.eobrazovanje.api.dto;

import com.ftn.eobrazovanje.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReqPaymentDTO {


    private Long id;

    private Long referenceNumber;

    private Double amount;

    private String text;

    private String accountNumber;

    private LocalDate paymentDate;
}
