package com.example.docker.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import com.example.docker.dto.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest webRequest) {

	    // 1. Extract the IP Address
		String ipAddress = ((ServletWebRequest)webRequest).getRequest().getRemoteAddr();

		// 2. Extract the Full Stack Trace as a String

	    StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    exception.printStackTrace(pw);
	    String fullStackTrace = sw.toString();

	    // 3. Create ErrorDetails
	    ErrorDetails errorDetails = new ErrorDetails(
	        LocalDateTime.now(), 
	        exception.getMessage(),
	        webRequest.getDescription(false),
	        HttpStatus.INTERNAL_SERVER_ERROR.value(),
	        ipAddress,
	        fullStackTrace

	    );

	    

	  return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        
    }
}
