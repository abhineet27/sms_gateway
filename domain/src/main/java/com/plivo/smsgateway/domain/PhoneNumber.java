/**
 * 
 */
package com.plivo.smsgateway.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author abhineet
 *
 */
@Entity(name="phone_number")
public class PhoneNumber {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	private String number;
	
	@Column(name="account_id")
	private Integer accountId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
}
