package com.ftn.eobrazovanje.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NotificationRequest {

    @NotNull(message = "Must select a course performance")
    private Long performanceId;

    @NotBlank(message = "Notification message is mandatory")
    private String message;

}
