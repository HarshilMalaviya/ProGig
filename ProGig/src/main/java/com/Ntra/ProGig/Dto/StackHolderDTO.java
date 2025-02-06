package com.Ntra.ProGig.Dto;

import com.Ntra.ProGig.Entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Data
public class StackHolderDTO {
    private int id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private long contact;
    private String password;
    private Role role;
}
