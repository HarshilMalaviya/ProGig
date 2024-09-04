package com.Ntra.ProGig.Dto;

import com.Ntra.ProGig.Entity.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;

    private List<String> skills;

    private String description;
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    private String status;

    private String whyRejected;
}
