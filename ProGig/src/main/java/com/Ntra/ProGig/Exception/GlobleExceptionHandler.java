package com.Ntra.ProGig.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobleExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ApiRequestException.class)
    public ResponseEntity<ApiException> handleAPIRequestException(ApiRequestException e){
        ApiException apiException=new ApiException(e.getMessage(),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiException,HttpStatus.NOT_FOUND);
  }


}
