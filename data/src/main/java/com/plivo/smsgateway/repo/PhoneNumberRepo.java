/**
 * 
 */
package com.plivo.smsgateway.repo;

import org.springframework.data.repository.CrudRepository;

import com.plivo.smsgateway.domain.PhoneNumber;

/**
 * @author abhineet
 *
 */
public interface PhoneNumberRepo extends CrudRepository<PhoneNumber, Integer> {

	public PhoneNumber findByNumber(String number);
}
