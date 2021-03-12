package com.automobil.backend.exeption;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_GATEWAY;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        ApiError apiError = new ApiError(status, "method arg not valid", ex.getMessage());

        return new ResponseEntity<Object>(apiError, BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
        EntityNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getClazzName(), ex.getLocalizedMessage());
        apiError.setMessage(ex.getMessage());
        return new ResponseEntity<Object>(apiError, BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                                      WebRequest request) {
        ApiError apiError = new ApiError();
        apiError.setMessage(String.format("The parameter '%s' of value '%s' could not be converted to type '%s'",
            ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName()));
        apiError.setErrors(Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(apiError, BAD_REQUEST);
    }
/* НЕ РАБОТАЕТ !!!!!!
    @ExceptionHandler({TransactionSystemException.class})
    public ResponseEntity<Object> handleConstraintViolation(
        Exception ex, WebRequest request) {
        Throwable cause = ((TransactionSystemException) ex).getRootCause();
        List<String> errors=new ArrayList<>();
        if (cause instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) cause).getConstraintViolations();
            errors = constraintViolations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        }
       ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return new ResponseEntity<>(apiError,BAD_GATEWAY);
    }
    @ExceptionHandler({ javax.validation.ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(
        ConstraintViolationException ex, WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " +
                violation.getPropertyPath() + ": " + violation.getMessage());
        }

        ApiError apiError =
            new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return new ResponseEntity<Object>(
            apiError, new HttpHeaders(), apiError.getStatus());
    }
  */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handleAll(Exception ex, WebRequest request) {
        ApiError apiError = new ApiError(
            HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "error occurred");
        return new ResponseEntity<>(
            apiError, new HttpHeaders(), apiError.getStatus());
    }
}
