/**
 * 
 */
package com.plivo.smsgateway.repo;

import org.springframework.data.repository.CrudRepository;

import com.plivo.smsgateway.domain.Account;

/**
 * @author abhineet
 *
 */
public interface AccountRepository extends CrudRepository<Account, Integer> {

	public Account findByUsername(String userName);
}
