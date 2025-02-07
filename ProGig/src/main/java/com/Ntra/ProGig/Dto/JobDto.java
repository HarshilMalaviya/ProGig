package com.Ntra.ProGig.Dto;

import com.Ntra.ProGig.Entity.Pay0ut_Methods;
import com.Ntra.ProGig.Entity.Proposals;
import jakarta.persistence.ElementCollection;
import lombok.Data;

import java.util.List;

@Data
public class JobDto {
    private int id;
    private String title;
    private String description;
    private List<String> skillsRequired;
    private String duration;
    private int amount;
    private Pay0ut_Methods payout_methods;
    private String providers_name;
    private String providers_email;
    private List<ProposalsDto> proposals;

}
