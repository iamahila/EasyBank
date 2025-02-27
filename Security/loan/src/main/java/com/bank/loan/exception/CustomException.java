package com.bank.loan.exception;


import com.bank.loan.dto.ErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class CustomException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(LoanAlreadyExistsException.class)
    public ResponseEntity<ErrorDTO> handleCardAlreadyExistsExceptions
            (LoanAlreadyExistsException ex, WebRequest webRequest){
        ErrorDTO errorDTO = new ErrorDTO(webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST, ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorDTO);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleResourceNotFoundExceptions(ResourceNotFoundException ex, WebRequest webRequest){
        ErrorDTO errorDTO = new ErrorDTO(webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST, ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleOtherExceptions(ResourceNotFoundException ex, WebRequest webRequest){
        ErrorDTO errorDTO = new ErrorDTO(webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST, ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorDTO);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, String> validationError = new HashMap<>();
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();

        errors.forEach(error -> {
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            validationError.put(fieldName, message);
        });

        return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);

    }

}
