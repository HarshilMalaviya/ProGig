package com.Ntra.ProGig.Dto;

import com.Ntra.ProGig.Entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
public class StackHolder2Dto {
    private int id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private long contact;
    private String password;
    private Role role;

}
