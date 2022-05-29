package com.ftn.eobrazovanje.service.impl;

import com.ftn.eobrazovanje.api.dto.PaymentAdminRequestDTO;
import com.ftn.eobrazovanje.api.dto.PaymentRespDTO;
import com.ftn.eobrazovanje.api.dto.ReqPaymentDTO;
import com.ftn.eobrazovanje.api.dto.mapper.PaymentMapper;
import com.ftn.eobrazovanje.api.dto.mapper.StudentMapper;
import com.ftn.eobrazovanje.model.FinancialCard;
import com.ftn.eobrazovanje.model.Payment;
import com.ftn.eobrazovanje.repository.PaymentRepository;
import com.ftn.eobrazovanje.service.PaymentService;
import com.ftn.eobrazovanje.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final StudentService studentService;
    private final FinancialCardService financialCardService;

    public PaymentServiceImpl(PaymentRepository paymentRepository, StudentService studentService, FinancialCardService financialCardService) {
        this.paymentRepository = paymentRepository;
        this.studentService = studentService;
        this.financialCardService = financialCardService;
    }

    @Override
    public PaymentRespDTO findAllByStudentId(Long id, Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Payment> payments = paymentRepository.findAllByStudentId(id, pageable);
        return new PaymentRespDTO(PaymentMapper.toDtoList(payments.getContent()), payments.getTotalPages());
    }

    @Override
    public Long create(ReqPaymentDTO paymentDTO) {
        Payment payment = PaymentMapper.reqToEntity(paymentDTO);
        payment.setStudent(StudentMapper.toEntity(studentService.findOneByReferenceNumber(paymentDTO.getReferenceNumber())));
        Payment created = paymentRepository.save(payment);
        return created.getId();
    }

    @Override
    public Long createByAdmin(PaymentAdminRequestDTO paymentDTO, Long id) {
        Payment created = createPayment(paymentDTO, id);
        FinancialCard financialCard = financialCardService.findByStudentId(id);
        financialCard.setBalance(financialCard.getBalance() + paymentDTO.getAmount().longValue());
        financialCardService.update(financialCard);
        return created.getId();
    }

    private Payment createPayment(PaymentAdminRequestDTO paymentDTO, Long id){
        Payment newPayment = new Payment();
        newPayment.setStudent(studentService.findByUserId(id));
        newPayment.setAmount(paymentDTO.getAmount());
        newPayment.setText(paymentDTO.getText());
        newPayment.setPaymentDate(LocalDate.now());
        Payment created = paymentRepository.save(newPayment);
        return created;
    }
}
