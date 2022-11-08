package com.kidusraguel.kidusraguel.util.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeInitializer {
    private Long id;
    private String title;
    private String hdate;
    private Long uid;
}
