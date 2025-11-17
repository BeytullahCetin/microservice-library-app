package com.turkcell.borrow_service.exception;

import jakarta.validation.ConstraintViolationException;
import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse(exception.getCode(), exception.getMessage(), Instant.now(), null));
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponse> handleBusiness(BusinessException exception) {
		return ResponseEntity.badRequest()
				.body(new ErrorResponse(exception.getCode(), exception.getMessage(), Instant.now(), null));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException exception) {
		Map<String, String> errors = exception.getBindingResult()
				.getFieldErrors()
				.stream()
				.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage, (existing, replacement) -> existing));
		return ResponseEntity.badRequest()
				.body(new ErrorResponse("VALIDATION_ERROR", "Request validation failed", Instant.now(), errors));
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException exception) {
		Map<String, String> errors = exception.getConstraintViolations()
				.stream()
				.collect(Collectors.toMap(
						constraintViolation -> constraintViolation.getPropertyPath().toString(),
						constraintViolation -> constraintViolation.getMessage(),
						(existing, replacement) -> existing));
		return ResponseEntity.badRequest()
				.body(new ErrorResponse("VALIDATION_ERROR", "Constraint validation failed", Instant.now(), errors));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGeneric(Exception exception) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ErrorResponse("UNEXPECTED_ERROR", exception.getMessage(), Instant.now(), null));
	}
}

