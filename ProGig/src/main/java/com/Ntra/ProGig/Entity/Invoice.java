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
    private String clientCompanyName;
    private String clientName;
    private String freelancerName;
    private String freelancerIdProof;
    private String milestoneTitle;
    private long milestonePaymentAmount;
    private long portalCommission;
    @CreatedDate
    @Temporal(TemporalType.DATE)
    private Date date;
//    private LocalDateTime dateTime;

}
