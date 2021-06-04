package com.whelp.demo.exception.handling;

import com.whelp.demo.exception.EmployeeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String EXCUSE = "Something went wrong. Please contact with me!";

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                               HttpHeaders headers, HttpStatus status,
                                                               WebRequest request) {
        Optional<String> optionalErrorMessage = e.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).findFirst();
        String errorMessage = EXCUSE;
        if (optionalErrorMessage.isPresent()) {
            errorMessage = optionalErrorMessage.get();
        }
        LOGGER.error("Validation error:", e);
        return new ResponseEntity<>(new RequestError(InternalErrorCode.INVALID_VALUE, errorMessage), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<RequestError> uspChallengeNotFoundExceptionHandle(EmployeeNotFoundException e) {
        LOGGER.error("Employee not found: {}", e.getMessage());
        return new ResponseEntity<>(new RequestError(InternalErrorCode.EMPLOYEE_NOT_FOUND, e.getMessage()), HttpStatus.NOT_FOUND);
    }

}