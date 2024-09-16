package com.emazon.microservicio_transaccion.configuration.exceptionhandler;

import com.emazon.microservicio_transaccion.domain.exception.NotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(exception.getMessage()),
                HttpStatus.NOT_FOUND.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ExceptionResponse> handleExpiredJwtException(ExpiredJwtException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(exception.getMessage()),
                HttpStatus.NOT_FOUND.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionResponse> handleNullPointerException(NullPointerException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(exception.getMessage()),
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ExceptionResponse> handleAuthorizationDeniedException(AuthorizationDeniedException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(exception.getMessage()),
                HttpStatus.FORBIDDEN.toString(), LocalDateTime.now()));
    }
}
