package com.tcs.cd.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.tcs.cd.constants.AppConstants;
import com.tcs.cd.dto.ResponseDTO;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	// Handle MethodArgumentNotValidException exception
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e,WebRequest request){
			LOG.error("GlobalExceptionHandler.handleMethodArgumentNotValidException : "+e.getMessage());
			ResponseDTO response = new ResponseDTO(AppConstants.RESPONSE_CODE_FAILURE,e.getBindingResult().getFieldError().getDefaultMessage());
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}

	// Handle CustomerNotFound exception
	@ExceptionHandler(CustomerNotFound.class)
	public ResponseEntity<?> handleCustomerNotFoundException(CustomerNotFound e,WebRequest request){
		LOG.error("GlobalExceptionHandler.handleCustomerNotFoundException : "+e.getMessage());
		ResponseDTO response = new ResponseDTO(AppConstants.RESPONSE_CODE_FAILURE,e.getMessage());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}

	// Handle InvalidCustomerData exception
	@ExceptionHandler(InvalidCustomerData.class)
	public ResponseEntity<?> handleInvalidCustomerDataException(InvalidCustomerData e,WebRequest request){
		ResponseDTO response = new ResponseDTO(AppConstants.RESPONSE_CODE_FAILURE,e.getMessage());
		LOG.error("GlobalExceptionHandler.handleInvalidCustomerDataException : "+e.getMessage());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}

	// Handle Global exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception e,WebRequest request){
		LOG.error("GlobalExceptionHandler.handleException : "+e.getMessage());
		ResponseDTO response = new ResponseDTO(AppConstants.RESPONSE_CODE_FAILURE,AppConstants.TECHNICAL_EXCEPTION);
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public GlobalExceptionHandler() {
		// TODO Auto-generated constructor stub
	}

}
