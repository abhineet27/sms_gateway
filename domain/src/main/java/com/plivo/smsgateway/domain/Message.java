/**
 * 
 */
package com.plivo.smsgateway.domain;

/**
 * @author abhineet
 *
 */
public class Message {

	private String from;
	private String to;
	private String text;
	
	
	public Message() {
		super();
	}

	public Message(String from, String to, String text) {
		super();
		this.from = from;
		this.to = to;
		this.text = text;
	}
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
