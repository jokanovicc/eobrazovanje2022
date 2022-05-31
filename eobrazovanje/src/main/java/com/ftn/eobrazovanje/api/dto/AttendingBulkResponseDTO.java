package com.ftn.eobrazovanje.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendingBulkResponseDTO {
    private List<String> added;
    private List<String> createdAndAdded;
    private List<String> alreadyAdded;
}
