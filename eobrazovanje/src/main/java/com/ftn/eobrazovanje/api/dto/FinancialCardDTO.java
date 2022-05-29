package com.ftn.eobrazovanje.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinancialCardDTO {
    private Long id;
    private Long balance;

}
