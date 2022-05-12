package com.ftn.eobrazovanje.api.dto;

import com.ftn.eobrazovanje.model.ExamPeriod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateExamRequest {
    @NotNull
    private Long performanceId;
    @NotNull
    private Long examPeriodId;

    private LocalDate date;

    private String classroom;

}
