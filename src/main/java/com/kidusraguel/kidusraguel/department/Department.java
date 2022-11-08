package com.kidusraguel.kidusraguel.department;

import com.kidusraguel.kidusraguel.employee.Employee;
import com.kidusraguel.kidusraguel.users.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long depid;
    private String depname;
    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName = "id")
    private Users uid;
    @ManyToOne
    @JoinColumn(name = "empid", referencedColumnName = "id")
    private Employee empid;
}
