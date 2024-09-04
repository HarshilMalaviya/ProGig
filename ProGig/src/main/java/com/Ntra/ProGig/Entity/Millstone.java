package com.Ntra.ProGig.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Millstone")
public class Millstone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "JobTitle")
    private String jobTitle;

    @Column(name = "ModuleCount")
    private Integer moduleCount;

    @Column(name = "ModuleName")
    private String moduleName;

    @Column(name = "Progress")
    private String progress;

    @Column(name = "Status")
    private String status;


}
