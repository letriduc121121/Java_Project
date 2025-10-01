package com.javaweb.model.response;

import java.util.List;

public class ErrorResponse {
	private String error;
	private List<String> details;
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public List<String> getDetails() {
		return details;
	}
	public void setDetails(List<String> details) {
		this.details = details;
	}
	
	
}
