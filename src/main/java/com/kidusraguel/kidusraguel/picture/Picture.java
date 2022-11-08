package com.kidusraguel.kidusraguel.picture;


import com.kidusraguel.kidusraguel.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "picture")
@Builder
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    @Lob
    @Column(name = "imagedata",length = 1000)
    private byte[] imageData;
    @ManyToOne
    @JoinColumn(name = "empid", referencedColumnName = "id")
    private Employee empid;
}
