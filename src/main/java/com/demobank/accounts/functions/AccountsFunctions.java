package com.demobank.accounts.functions;

import com.demobank.accounts.service.AccountsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class AccountsFunctions {
    @Bean
    public Consumer<Long> updateCommunication(AccountsService accountsService){
        return accountNumber->{
            log.info("Updating Communication status for the account number: {}",accountNumber);
            accountsService.updateCommunicationStatus(accountNumber);

        };
    }
}
