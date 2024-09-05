package com.Ntra.ProGig.Dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Date;

@Data
public class InvoiceDto {
    private int id;
    private String ClientCompanyName;
    private String ClientName;
    private String FreelancerName;
    private String FreelancerIdProfe;
    private String MilestoneTitle;
    private long MilestonePaymentAmount;
    private long PortalCommission;
    @CreatedDate
    @Temporal(TemporalType.DATE)
    private Date date;


}
