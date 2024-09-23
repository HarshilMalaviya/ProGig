package com.Ntra.ProGig.Dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class MillstoneDto {
    private Integer id;

    private String jobTitle;

    private Integer moduleCount;

    private String moduleName;

    private String progress;

    private String status;
}
