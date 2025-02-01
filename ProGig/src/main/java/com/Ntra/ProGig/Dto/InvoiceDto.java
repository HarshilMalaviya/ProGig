package com.Ntra.ProGig.Dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Date;

@Data
public class InvoiceDto {
    private int id;
    private String clientCompanyName;
    private String clientName;
    private String freelancerName;
    private long freelancerIdProof;
    private String milestoneTitle;
    private long milestonePaymentAmount;
    private long portalCommission;
    @CreatedDate
    @Temporal(TemporalType.DATE)
    private Date date;


}
