package com.ftn.eobrazovanje.service;

import com.ftn.eobrazovanje.api.dto.ReqPaymentDTO;

import java.util.List;

public interface PaymentService {

    List<ReqPaymentDTO> findAllByStudentId(Long id);

    Long create(ReqPaymentDTO paymentDTO);

}
