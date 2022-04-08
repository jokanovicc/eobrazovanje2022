package com.ftn.eobrazovanje.service.impl;

import com.ftn.eobrazovanje.api.dto.ReqPaymentDTO;
import com.ftn.eobrazovanje.api.dto.mapper.PaymentMapper;
import com.ftn.eobrazovanje.model.Payment;
import com.ftn.eobrazovanje.repository.PaymentRepository;
import com.ftn.eobrazovanje.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<ReqPaymentDTO> findAllByStudentId(Long id) {
        List<ReqPaymentDTO> paymentDTOS = PaymentMapper.toDtoList(paymentRepository.findAllByStudentId(id));
        return paymentDTOS;
    }

    @Override
    public Long create(ReqPaymentDTO paymentDTO) {
        Payment payment = PaymentMapper.reqToEntity(paymentDTO);
        Payment created = paymentRepository.save(payment);
        return created.getId();
    }
}
