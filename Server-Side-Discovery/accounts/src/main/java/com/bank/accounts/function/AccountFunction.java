package com.bank.accounts.function;

import com.bank.accounts.business.AccountBusiness;
import com.bank.accounts.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class AccountFunction {

    private static final Logger log = LoggerFactory.getLogger(AccountFunction.class);

    @Bean
    public Consumer<Long> updateCommunication(AccountBusiness accountBusiness) {
        return accountNumber -> {
            log.info("Updating Communication status for the account number : " + accountNumber.toString());
            boolean isUpdated = accountBusiness.updateCommunicationStatus(accountNumber);

            log.info("Updating flag : " + isUpdated);
        };
    }
}
