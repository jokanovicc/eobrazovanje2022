package com.ftn.eobrazovanje.service;

import com.ftn.eobrazovanje.api.dto.PaymentAdminRequestDTO;
import com.ftn.eobrazovanje.api.dto.PaymentRespDTO;
import com.ftn.eobrazovanje.api.dto.ReqPaymentDTO;
import com.ftn.eobrazovanje.api.dto.RespPaymentDTO;

import java.util.List;

public interface PaymentService {

    PaymentRespDTO findAllByStudentId(Long id, Integer page);

    Long create(ReqPaymentDTO paymentDTO);

    Long createByAdmin(PaymentAdminRequestDTO paymentDTO, Long id);

}
