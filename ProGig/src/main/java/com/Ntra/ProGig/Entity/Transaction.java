package com.Ntra.ProGig.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    private String clientName;

    private String freelancerName;

    private String paymentAmount;

    private Long transactionId;

    private String contract;

    private Date transactionDate;

    private String jobTitle;

    private String jobDescription;
}
