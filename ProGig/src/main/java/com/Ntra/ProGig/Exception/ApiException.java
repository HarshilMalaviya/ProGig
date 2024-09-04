package com.Ntra.ProGig.Exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class ApiException {
    private final String massage;
    private final HttpStatus httpStatus;

    public ApiException(String massage, HttpStatus httpStatus) {
        this.massage = massage;
        this.httpStatus = httpStatus;
    }
}
