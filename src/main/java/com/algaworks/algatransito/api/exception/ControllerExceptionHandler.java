package com.algaworks.algatransito.api.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.exception.ValidationError;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    
    private final MessageSource messageSource;

    @ExceptionHandler(exception = NegocioException.class)
    public ProblemDetail handleNegocioException(NegocioException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        problemDetail.setTitle(ex.getMessage());

        return problemDetail;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        
        List<ValidationError> fields = ex.getBindingResult().getAllErrors()
            .stream()
            .map(error -> new ValidationError(
                error.getObjectName(), 
                ((FieldError) error).getField(), 
                messageSource.getMessage(error, LocaleContextHolder.getLocale())))
            .toList();
        
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        problemDetail.setTitle("Um ou mais campos possuem valores inválidos.");
        problemDetail.setProperty("fields", fields);

        return super.handleExceptionInternal(ex, problemDetail, headers, status, request);
    }

    @ExceptionHandler(exception = DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        problemDetail.setTitle("O recurso se encontra em uso.");

        return problemDetail;
    }
    
}
