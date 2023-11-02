package com.Blog.Blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ApiResponse> resourceNotFound(ResourceNotFound e){
        String msg=e.getMessage();
        ApiResponse apiResponse=new ApiResponse(msg, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ResourceAlreadyExist.class)
    public ResponseEntity<ApiResponse> resourceAlreadyExists(ResourceAlreadyExist e){
        String msg=e.getMessage();
        ApiResponse apiResponse=new ApiResponse(msg,HttpStatus.ALREADY_REPORTED);
        return new ResponseEntity<>(apiResponse,HttpStatus.ALREADY_REPORTED);
    }

}
