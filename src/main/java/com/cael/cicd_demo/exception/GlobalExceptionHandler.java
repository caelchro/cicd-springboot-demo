package com.cael.cicd_demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cael.cicd_demo.usuario.UsuarioNotFoundException;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UsuarioNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleUsuarioNotFound(UsuarioNotFoundException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errores = new HashMap<>();
		System.err.println("handleValidationExceptions:" + ex.getMessage());

		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errores.put(error.getField(), error.getDefaultMessage());
			System.err.println("handleValidationExceptions:" + error.getField() + "::" + error.getDefaultMessage());
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
	}

	// 2. Captura errores en parámetros sueltos (@RequestParam, @PathVariable o
	// métodos de servicio)
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String, String>> handleConstraintViolation(ConstraintViolationException ex) {
		Map<String, String> errores = new HashMap<>();
		System.err.println("handleConstraintViolation:" + ex.getMessage());

		ex.getConstraintViolations().forEach(violation -> {
			String campo = violation.getPropertyPath().toString();
			String mensaje = violation.getMessage();
			errores.put(campo, mensaje);
		});

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
	}
}
