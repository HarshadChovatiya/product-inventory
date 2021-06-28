package com.order.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.order.model.ErrorDetail;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetail> handleException(Exception ex, WebRequest request) {
	    ErrorDetail errorDetail = new ErrorDetail(new Date(),
	    		ex.getMessage(),
	    		request.getDescription(false));
		return new ResponseEntity<ErrorDetail>(errorDetail, HttpStatus.BAD_REQUEST);
	}
	
}