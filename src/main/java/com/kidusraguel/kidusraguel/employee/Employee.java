package com.kidusraguel.kidusraguel.employee;

import com.kidusraguel.kidusraguel.users.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    private Long id;
    private String title;
    private String hdate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="uid")
    private Users uid;

}
