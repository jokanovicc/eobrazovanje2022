package com.ftn.eobrazovanje.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class RespPaymentDTO {


    private Long id;

    private StudentDTO student;

    private Double amount;

    private String text;

    private String accountNumber;

    private LocalDate paymentDate;
}
