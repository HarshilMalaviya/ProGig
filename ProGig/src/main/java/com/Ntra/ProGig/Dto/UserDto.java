package com.Ntra.ProGig.Dto;

import com.Ntra.ProGig.Entity.Profile;
import com.Ntra.ProGig.Entity.UserRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private Long phone;

    private String email;

    private String username;

    private String password;

    private List<String> skills;

    private String description;

    private UserRole role;

    private String status;

    private String whyRejected;

    @OneToOne
    private Profile profile;
}
