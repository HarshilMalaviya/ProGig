package com.Ntra.ProGig.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "bank_details")
public class BankDerails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String accountHolderName;
    private String accountNumber;
    private String ifscCode;
    private String bankName;
    private String branchName;
}
