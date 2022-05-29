package com.ftn.eobrazovanje.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentAdminRequestDTO {
    @NotBlank
    private String text;

    @Min(300)
    private Double amount;
}
