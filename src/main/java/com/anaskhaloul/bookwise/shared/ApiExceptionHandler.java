package com.anaskhaloul.bookwise.shared;

import com.anaskhaloul.bookwise.catalog.*;
import com.anaskhaloul.bookwise.loan.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.*;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(BookNotFoundException.class)
    ResponseEntity<ApiError> notFound(RuntimeException ex) { return response(HttpStatus.NOT_FOUND, "BOOK_NOT_FOUND", ex.getMessage()); }
    @ExceptionHandler(DuplicateIsbnException.class)
    ResponseEntity<ApiError> conflict(RuntimeException ex) { return response(HttpStatus.CONFLICT, "DUPLICATE_ISBN", ex.getMessage()); }
    @ExceptionHandler({MemberNotFoundException.class,LoanNotFoundException.class})
    ResponseEntity<ApiError> lendingNotFound(RuntimeException ex) { return response(HttpStatus.NOT_FOUND, "RESOURCE_NOT_FOUND", ex.getMessage()); }
    @ExceptionHandler(DuplicateEmailException.class)
    ResponseEntity<ApiError> duplicateEmail(RuntimeException ex) { return response(HttpStatus.CONFLICT, "DUPLICATE_EMAIL", ex.getMessage()); }
    @ExceptionHandler(IllegalStateException.class)
    ResponseEntity<ApiError> invalidState(IllegalStateException ex) { return response(HttpStatus.CONFLICT, "INVALID_LOAN_STATE", ex.getMessage()); }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiError> invalid(MethodArgumentNotValidException ex) {
        Map<String,String> fields = new TreeMap<>();
        ex.getBindingResult().getFieldErrors().forEach(e -> fields.putIfAbsent(e.getField(), e.getDefaultMessage()));
        return ResponseEntity.badRequest().body(new ApiError(Instant.now(), 400, "VALIDATION_FAILED", "Request validation failed", fields));
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity<ApiError> integrity() { return response(HttpStatus.CONFLICT, "DATA_CONFLICT", "The request conflicts with existing data"); }
    private ResponseEntity<ApiError> response(HttpStatus status, String code, String message) {
        return ResponseEntity.status(status).body(new ApiError(Instant.now(), status.value(), code, message, Map.of()));
    }
    public record ApiError(Instant timestamp, int status, String code, String message, Map<String,String> fields) { }
}
