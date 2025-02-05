package com.Ntra.ProGig.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User")

public class User{
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    private Integer phone;

    private String email;

    private String username;

    private String password;

    private List<String> skills;

    private String description;

    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    private String status;

    private String whyRejected;

    @OneToOne( cascade = CascadeType.ALL)
    private Profile profile;

    }


