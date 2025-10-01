package com.javaweb.controlleradvice;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.javaweb.exception.InvalidBuildingException;
import com.javaweb.model.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionAdvice {
	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<ErrorResponse> handleArithmeticException(ArithmeticException ex){
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setError(ex.getMessage());
		errorResponse.setDetails(Arrays.asList("Số nguyên làm sao chia cho 0 được"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}
	@ExceptionHandler(InvalidBuildingException.class)
	public ResponseEntity<ErrorResponse> handleInvalidBuildingException(InvalidBuildingException ex){
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setError("Invalid Data Building");
		errorResponse.setDetails(Arrays.asList(ex.getMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception ex){
		ErrorResponse errorResponse =new ErrorResponse();
		errorResponse.setError(ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}
}
