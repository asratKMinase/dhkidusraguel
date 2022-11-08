package com.kidusraguel.kidusraguel.util.web.dto;

import com.kidusraguel.kidusraguel.users.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressInitializer {
    private Long addressid;
    private String address;
    private String address2;
    private String city;
    private String state;
    private String zip;
     private Long id;

}
