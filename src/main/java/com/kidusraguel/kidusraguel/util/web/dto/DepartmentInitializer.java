package com.kidusraguel.kidusraguel.util.web.dto;

import com.kidusraguel.kidusraguel.employee.Employee;
import com.kidusraguel.kidusraguel.users.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DepartmentInitializer {

    private Long depid;
    private String depname;
    private Long uid;
    private Long empid;
}
