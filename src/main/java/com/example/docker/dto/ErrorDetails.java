package com.example.docker.dto;

import java.time.LocalDateTime;




public class ErrorDetails {

	private LocalDateTime timestamp;
    private String message;
    private String details;
    private Integer statusCode;
    private String ipAddress;
    private String stackTrace;

    

    

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

    

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	

	public ErrorDetails(LocalDateTime timestamp, String message, String details, Integer statusCode, String ipAddress,String stackTrace) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.statusCode = statusCode;
		this.ipAddress=ipAddress;
		this.stackTrace=stackTrace;

	}
}
