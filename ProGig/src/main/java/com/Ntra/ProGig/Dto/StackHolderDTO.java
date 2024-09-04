package com.Ntra.ProGig.Dto;

import com.Ntra.ProGig.Entity.Role;
import lombok.Data;

@Data
public class StackHolderDTO {
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private long contact;
    private String password;
    private Role role;
}
