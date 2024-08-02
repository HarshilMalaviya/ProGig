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
@Table(name = "Contract")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "FreelancerName")
    private String freelancerName;

    @Column(name = "ClientName")
    private String clientName;

    @Column(name = "JobTitle")
    private String jobTitle;

    @Column(name = "Description")
    private String description;

    @Column(name = "JobAmount")
    private String jobAmount;

    @Column(name = "StartingDate")
    private Date startingDate;

    @Column(name = "EndingDate")
    private Date endingDate;

}
