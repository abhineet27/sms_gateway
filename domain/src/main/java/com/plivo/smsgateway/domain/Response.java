/**
 * 
 */
package com.plivo.smsgateway.domain;

/**
 * @author abhineet
 *
 */
public class Response {

	private String message;
	private String error;
	
	
	public Response() {
		super();
	}
	
	public Response(String message, String error) {
		super();
		this.message = message;
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
}
