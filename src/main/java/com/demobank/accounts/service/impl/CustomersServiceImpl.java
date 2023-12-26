package com.demobank.accounts.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demobank.accounts.dto.AccountsDto;
import com.demobank.accounts.dto.CardsDto;
import com.demobank.accounts.dto.CustomerDetailsDto;
import com.demobank.accounts.dto.LoansDto;
import com.demobank.accounts.entity.Accounts;
import com.demobank.accounts.entity.Customer;
import com.demobank.accounts.exception.ResourceNotFoundException;
import com.demobank.accounts.mapper.AccountsMapper;
import com.demobank.accounts.mapper.CustomerMapper;
import com.demobank.accounts.repository.AccountsRepository;
import com.demobank.accounts.repository.CustomerRepository;
import com.demobank.accounts.service.ICustomersService;
import com.demobank.accounts.service.client.CardsFeignClient;
import com.demobank.accounts.service.client.LoansFeignClient;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

	private AccountsRepository accountsRepository;
	private CustomerRepository customerRepository;
	private CardsFeignClient cardsFeignClient;
	private LoansFeignClient loansFeignClient;

	/**
	 * @param mobileNumber - Input Mobile Number
	 * @return Customer Details based on a given mobileNumber
	 */
	@Override
	public CustomerDetailsDto fetchCustomerDetails(String correlationId, String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
				() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));

		CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer,
				new CustomerDetailsDto());
		customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

		ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId,
				mobileNumber);
		if (null != loansDtoResponseEntity)
			customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

		ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId,
				mobileNumber);
		if (null != cardsDtoResponseEntity)
			customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

		return customerDetailsDto;

	}
}
