package com.Ntra.ProGig.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class ContractDto {

    private Integer id;

    private String freelancerName;

    private String clientName;

    private String jobTitle;

    private String description;

    private String jobAmount;

    private Date startingDate;
    
    private Date endingDate;
}
