package com.eiericksilva.controle_financeiro.controllers.exceptions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.eiericksilva.controle_financeiro.exceptions.InsufficientBalanceException;
import com.eiericksilva.controle_financeiro.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<SchemaError> handleResourceNotFound(ResourceNotFoundException e,
                        HttpServletRequest request) {
                HttpStatus status = HttpStatus.NOT_FOUND;
                SchemaError err = new SchemaError(
                                LocalDateTime.now(),
                                status.value(),
                                List.of(e.getMessage()),
                                request.getRequestURI());

                return ResponseEntity.status(status).body(err);
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<SchemaError> constraintViolationException(MethodArgumentNotValidException e,
                        HttpServletRequest request) {
                HttpStatus status = HttpStatus.BAD_REQUEST;
                List<String> errorMessages = e.getAllErrors().stream()
                                .map(error -> error.getDefaultMessage())
                                .collect(Collectors.toList());

                SchemaError err = new SchemaError(
                                LocalDateTime.now(),
                                status.value(),
                                errorMessages,
                                request.getRequestURI());

                return ResponseEntity.status(status).body(err);
        }

        @ExceptionHandler(InsufficientBalanceException.class)
        public ResponseEntity<SchemaError> handleInsufficientBalanceException(InsufficientBalanceException e,
                        HttpServletRequest request) {
                HttpStatus status = HttpStatus.NOT_FOUND;
                SchemaError err = new SchemaError(
                                LocalDateTime.now(),
                                status.value(),
                                List.of(e.getMessage()),
                                request.getRequestURI());

                return ResponseEntity.status(status).body(err);
        }

}
