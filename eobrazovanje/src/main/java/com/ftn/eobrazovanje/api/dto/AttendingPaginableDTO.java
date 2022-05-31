package com.ftn.eobrazovanje.api.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendingPaginableDTO {
    List<AttendingDTO> attendings;
    int pageCount;
}
