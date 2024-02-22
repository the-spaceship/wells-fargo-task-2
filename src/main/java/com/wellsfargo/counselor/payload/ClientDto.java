package com.wellsfargo.counselor.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private long client_id;
    private String first_name;
    private String last_name;
    private String address;
    private String phone;
    private String email;
}
