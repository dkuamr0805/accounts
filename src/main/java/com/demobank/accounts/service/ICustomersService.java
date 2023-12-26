package com.demobank.accounts.service;

import com.demobank.accounts.dto.CustomerDetailsDto;

public interface ICustomersService {

	/**
	 *
	 * @param mobileNumber - Input Mobile Number
	 * @return Customer Details based on a given mobileNumber
	 */
	CustomerDetailsDto fetchCustomerDetails(String correlationId, String mobileNumber);
}