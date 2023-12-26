package com.demobank.accounts.service.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.demobank.accounts.dto.LoansDto;

@Component
public class LoansFallback implements LoansFeignClient {

	@Override
	public ResponseEntity<LoansDto> fetchLoanDetails(String correlationId, String mobileNumber) {
		return null;
	}

}
