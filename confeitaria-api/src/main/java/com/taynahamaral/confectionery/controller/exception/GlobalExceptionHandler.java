package com.taynahamaral.confectionery.controller.exception;

import com.taynahamaral.confectionery.domain.user.exception.InvalidCredentialsException;
import com.taynahamaral.confectionery.domain.user.exception.UserAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ---------- UserAlreadyExists ----------
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExists(
            UserAlreadyExistsException ex,
            HttpServletRequest request
    ) {
        ErrorResponse error = buildErrorResponse(
                ex.getMessage(),
                request,
                HttpStatus.CONFLICT,
                ErrorCode.USER_EMAIL_ALREADY_EXISTS,
                null
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    // ---------- InvalidCredentials ----------
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentials(
            InvalidCredentialsException ex,
            HttpServletRequest request
    ) {
        ErrorResponse error = buildErrorResponse(
                ex.getMessage(),
                request,
                HttpStatus.UNAUTHORIZED,
                ErrorCode.INVALID_CREDENTIALS,
                null
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    // ---------- Validation Errors ----------
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(fieldError ->
                        errors.put(fieldError.getField(), fieldError.getDefaultMessage())
                );

        ErrorResponse error = buildErrorResponse(
                "Invalid request fields",
                request,
                HttpStatus.BAD_REQUEST,
                ErrorCode.VALIDATION_ERROR,
                errors
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // ---------- Generic Exception ----------
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex,
            HttpServletRequest request
    ) {
        ErrorResponse error = buildErrorResponse(
                "Unexpected error occurred",
                request,
                HttpStatus.INTERNAL_SERVER_ERROR,
                ErrorCode.INTERNAL_SERVER_ERROR,
                null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    // ---------- Utility Method ----------
    private ErrorResponse buildErrorResponse(
            String message,
            HttpServletRequest request,
            HttpStatus status,
            ErrorCode code,
            Map<String, String> fields
    ) {
        return new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                request.getRequestURI(),
                code,
                fields
        );
    }
}
