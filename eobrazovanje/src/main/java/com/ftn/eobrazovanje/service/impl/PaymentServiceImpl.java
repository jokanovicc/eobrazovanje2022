package com.ftn.eobrazovanje.service.impl;

import com.ftn.eobrazovanje.api.dto.ReqPaymentDTO;
import com.ftn.eobrazovanje.api.dto.RespPaymentDTO;
import com.ftn.eobrazovanje.api.dto.mapper.PaymentMapper;
import com.ftn.eobrazovanje.api.dto.mapper.StudentMapper;
import com.ftn.eobrazovanje.model.Payment;
import com.ftn.eobrazovanje.repository.PaymentRepository;
import com.ftn.eobrazovanje.service.PaymentService;
import com.ftn.eobrazovanje.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final StudentService studentService;

    public PaymentServiceImpl(PaymentRepository paymentRepository, StudentService studentService) {
        this.paymentRepository = paymentRepository;
        this.studentService = studentService;
    }

    @Override
    public List<RespPaymentDTO> findAllByStudentId(Long id) {
        List<RespPaymentDTO> paymentDTOS = PaymentMapper.toDtoList(paymentRepository.findAllByStudentId(id));
        return paymentDTOS;
    }

    @Override
    public Long create(ReqPaymentDTO paymentDTO) {
        Payment payment = PaymentMapper.reqToEntity(paymentDTO);
        payment.setStudent(StudentMapper.toEntity(studentService.findOneByReferenceNumber(paymentDTO.getReferenceNumber())));
        Payment created = paymentRepository.save(payment);
        return created.getId();
    }
}
