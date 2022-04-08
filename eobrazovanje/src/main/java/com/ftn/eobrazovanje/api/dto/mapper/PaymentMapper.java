package com.ftn.eobrazovanje.api.dto.mapper;

import com.ftn.eobrazovanje.api.dto.ReqPaymentDTO;
import com.ftn.eobrazovanje.model.Payment;

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
                dto.getPaymentDate()
                );
    }
    //TODO zavrsiti
    public static ReqPaymentDTO reqToDto(Payment payment) {
        return new ReqPaymentDTO(
                payment.getId(),
                StudentMapper.toDto(payment.getStudent()),
                payment.getAmount(),
                payment.getText(),
                payment.getAccountNumber(),
                payment.getPaymentDate()
        );
    }

    public static List<ReqPaymentDTO> toDtoList(List<Payment> payments) {
        return payments
                .stream()
                .map(payment -> toDto(payment))
                .collect(Collectors.toList());
    }
}
