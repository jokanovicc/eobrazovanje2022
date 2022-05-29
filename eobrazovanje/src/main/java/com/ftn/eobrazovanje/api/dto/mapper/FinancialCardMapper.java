package com.ftn.eobrazovanje.api.dto.mapper;

import com.ftn.eobrazovanje.api.dto.FinancialCardDTO;
import com.ftn.eobrazovanje.api.dto.NotificationDTO;
import com.ftn.eobrazovanje.model.FinancialCard;
import com.ftn.eobrazovanje.model.Notification;

public class FinancialCardMapper {

    public static FinancialCardDTO toDto(FinancialCard financialCard) {
        return new FinancialCardDTO(
                financialCard.getId(),
                financialCard.getBalance()
        );
    }
}
