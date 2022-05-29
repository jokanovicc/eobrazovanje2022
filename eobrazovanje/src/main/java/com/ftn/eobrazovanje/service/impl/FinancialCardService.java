package com.ftn.eobrazovanje.service.impl;

import com.ftn.eobrazovanje.api.dto.FinancialCardDTO;
import com.ftn.eobrazovanje.api.dto.mapper.FinancialCardMapper;
import com.ftn.eobrazovanje.model.FinancialCard;
import com.ftn.eobrazovanje.repository.FinancialCardRepository;
import org.springframework.stereotype.Service;

@Service
public class FinancialCardService {

    private final FinancialCardRepository financialCardRepository;

    public FinancialCardService(FinancialCardRepository financialCardRepository) {
        this.financialCardRepository = financialCardRepository;
    }

    public FinancialCardDTO findByStudent(Long id){
       return FinancialCardMapper.toDto(financialCardRepository.findByStudentId(id).orElse(null));
    }

    public FinancialCard findByStudentId(Long id){
        return financialCardRepository.findByStudentId(id).orElse(null);
    }

    public void update(FinancialCard financialCard){
        financialCardRepository.save(financialCard);
    }
}
