package com.ftn.eobrazovanje.api.dto.mapper;

import com.ftn.eobrazovanje.api.dto.CourseDTO;
import com.ftn.eobrazovanje.api.dto.ReqPaymentDTO;
import com.ftn.eobrazovanje.api.dto.RespPaymentDTO;
import com.ftn.eobrazovanje.model.Course;
import com.ftn.eobrazovanje.model.Payment;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentMapper {

    public static Payment reqToEntity(ReqPaymentDTO dto) {
        return new Payment(
                dto.getId(),
                null,
                dto.getAmount(),
                dto.getText(),
                dto.getAccountNumber(),
                LocalDate.now()
                );
    }
    public static RespPaymentDTO respToDto(Payment payment) {
        return new RespPaymentDTO(
                payment.getId(),
                StudentMapper.toDto(payment.getStudent()),
                payment.getAmount(),
                payment.getText(),
                payment.getAccountNumber(),
                payment.getPaymentDate()
        );
    }

    public static List<RespPaymentDTO> toDtoList(List<Payment> payments) {
        return payments
                .stream()
                .map(payment -> respToDto(payment))
                .collect(Collectors.toList());
    }

}
