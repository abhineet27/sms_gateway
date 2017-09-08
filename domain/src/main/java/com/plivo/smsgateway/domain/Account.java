/**
 * 
 */
package com.plivo.smsgateway.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author abhineet
 *
 */
@Entity(name="account")
public class Account {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	private String authId;
	
	private String username;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
