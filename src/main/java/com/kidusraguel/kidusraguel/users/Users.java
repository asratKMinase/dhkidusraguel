package com.kidusraguel.kidusraguel.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Column(name = "fname", length = 50, nullable = false)
    private String fname;
    private String mitial;
    @Column(name = "lname", length = 50, nullable = false)
    private String lname;
    @Column(name = "kname", length = 50, nullable = false)
    private String kname;
    @Column(name = "gender")
    private String gender;
    @Column(name = "phone", nullable = false)
    private String phone;
    private String rdate;
    private String dob;
    @Column(unique = true, nullable = false)
    private String email;
    //private Long addressid;

}
