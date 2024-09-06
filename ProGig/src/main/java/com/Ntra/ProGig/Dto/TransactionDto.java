package com.Ntra.ProGig.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class TransactionDto {
    private Integer id;

    private String clientName;

    private String freelancerName;

    private String paymentAmount;

    private String contract;

    private Date transactionDate;

    private String jobTitle;

    private String jobDescription;

}
