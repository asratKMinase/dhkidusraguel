package com.kidusraguel.kidusraguel.address;

import com.kidusraguel.kidusraguel.users.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressid;
    private String address;
    private String address2;
    private String city;
    private String state;
    private String zip;
    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Users id;

}
