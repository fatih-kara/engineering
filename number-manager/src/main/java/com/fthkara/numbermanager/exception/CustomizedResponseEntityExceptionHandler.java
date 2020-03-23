package com.fthkara.numbermanager.exception;

import com.fthkara.numbermanager.model.ExceptionDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public final ResponseEntity<Object> handleAllExceptions(ApiException ex, WebRequest request) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(ex.getMessage(), ex.getStatusCode().value(), request.getDescription(false), new Date());
        return new ResponseEntity<>(exceptionDetails, ex.getStatusCode());
    }
}
