package com.Ntra.ProGig.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProposalsDto {

    private String freelancerName;

    private String freelancerEmail;

    private Long bid;

    private Date finishingTime;

    private Integer review;


}
