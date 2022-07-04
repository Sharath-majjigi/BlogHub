package com.example.sharath.BlogHub.Exception;

import com.example.sharath.BlogHub.DTOs.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionhandler(ResourceNotFoundException e){
        String message = e.getMessage();
        ApiResponse response = new ApiResponse(message,false);
        return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException e){
        Map<String,String> res = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName=((FieldError)error).getField();
            String message=error.getDefaultMessage();
            res.put(fieldName,message);
        });
        return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
    }
}
