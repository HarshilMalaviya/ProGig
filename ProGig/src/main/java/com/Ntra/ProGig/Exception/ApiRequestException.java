package com.Ntra.ProGig.Exception;

import org.springframework.http.HttpStatus;

public class ApiRequestException extends RuntimeException {
    public ApiRequestException(String message) {
        super(message);
    }

}
