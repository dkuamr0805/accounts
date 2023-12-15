package com.demobank.accounts.service;

import com.demobank.accounts.dto.CustomerDto;

public interface AccountsService {
	/**
	 *
	 * @param customerDto - CustomerDto Object
	 */
	void createAccount(CustomerDto customerDto);

	/**
	 *
	 * @param mobileNumber - Input Mobile Number
	 * @return Accounts Details based on a given mobileNumber
	 */
	CustomerDto fetchAccount(String mobileNumber);

	/**
	 *
	 * @param customerDto - CustomerDto Object
	 * @return boolean indicating if the update of Account details is successful or
	 *         not
	 */
	boolean updateAccount(CustomerDto customerDto);

	/**
	 *
	 * @param mobileNumber - Input Mobile Number
	 * @return boolean indicating if the delete of Account details is successful or
	 *         not
	 */
	boolean deleteAccount(String mobileNumber);

}
