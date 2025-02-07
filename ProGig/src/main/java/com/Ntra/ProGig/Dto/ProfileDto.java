package com.Ntra.ProGig.Dto;


import com.Ntra.ProGig.Entity.BankDerails;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class ProfileDto {
    private Integer id;
    //  freelancer + client basic profile
//    private String Address;
    private String zipCode;
    private String city;
    private String State;
    private String Country;
    //  Client detail
    private String CompanyName;
    private String Location;
    //    Add ON Info
    private String Experience;
    private String Education;
    private String Articles;
    private String Certification;
    private BankDerails bank;
    //    Aditional Section
//    private List<Review> review;
//    private Portfolio portfolio;


}

