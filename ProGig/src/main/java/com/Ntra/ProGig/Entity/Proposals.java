package com.Ntra.ProGig.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Proposals")
public class Proposals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    private String jobTitle;

    private String freelancerName;

    private String freelancerEmail;

    private Long bid;

    private Date finishingTime;

    private Integer review;


}
