package com.Ntra.ProGig.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobleExceptionHandler  {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleAPIRequestException( UserNotFoundException e){
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(),HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(OkStatus.class)
    public ResponseEntity<ErrorResponse> handleAPIRequestException(OkStatus e){
        ErrorResponse errorResponse=new ErrorResponse(e.getMessage(),HttpStatus.OK);
        return new ResponseEntity<>(errorResponse,HttpStatus.OK);
    }
    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<ErrorResponse> handleAPIRequestException(NoContentException e){
        ErrorResponse errorResponse=new ErrorResponse(e.getMessage(),HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(errorResponse,HttpStatus.NO_CONTENT);
    }



}
