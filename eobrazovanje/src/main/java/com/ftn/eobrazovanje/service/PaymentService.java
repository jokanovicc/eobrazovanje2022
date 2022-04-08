package com.ftn.eobrazovanje.service;

import com.ftn.eobrazovanje.api.dto.ReqPaymentDTO;
import com.ftn.eobrazovanje.api.dto.RespPaymentDTO;

import java.util.List;

public interface PaymentService {

    List<RespPaymentDTO> findAllByStudentId(Long id);

    Long create(ReqPaymentDTO paymentDTO);

}
