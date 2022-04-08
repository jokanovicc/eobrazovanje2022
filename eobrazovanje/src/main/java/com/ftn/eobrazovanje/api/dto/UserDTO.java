package com.ftn.eobrazovanje.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String lastname;
    private String jmbg;
    private String address;
    private String username;
    private String gender;

}
