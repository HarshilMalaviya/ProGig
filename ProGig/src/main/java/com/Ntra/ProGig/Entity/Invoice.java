package com.Ntra.ProGig.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Date;


@Entity
@Data
@Table(name = "Invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
//    private LocalDateTime dateTime;

}
