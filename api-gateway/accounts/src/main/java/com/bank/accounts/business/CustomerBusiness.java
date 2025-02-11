package com.bank.accounts.business;

import com.bank.accounts.client.CardsFeignClient;
import com.bank.accounts.client.LoanFeignClient;
import com.bank.accounts.dto.*;
import com.bank.accounts.entity.AccountEntity;
import com.bank.accounts.entity.CustomerEntity;
import com.bank.accounts.exception.ResourceNotFoundException;
import com.bank.accounts.mapper.AccountsMapper;
import com.bank.accounts.mapper.CustomerMapper;
import com.bank.accounts.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class CustomerBusiness {

    private AccountService accountService;

    private CardsFeignClient cardsFeignClient;

    private LoanFeignClient loanFeignClient;

    public CustomerInfoDTO getCustomerInfo(String correlationId, String mobileNumber){
        CustomerInfoDTO customerInfoDTO = new CustomerInfoDTO();

        CustomerEntity customer = accountService.getCustomerData(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber));

        AccountEntity account = accountService.getAccountData(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "CustomerId",
                        customer.getCustomerId().toString()));

        CustomerDTO customerDTO = CustomerMapper.mapToCustomerDto(customer, new CustomerDTO());

        AccountDTO accountDTO = AccountsMapper.mapToAccountsDto(account, new AccountDTO());

        LoanDTO loanDTO = loanFeignClient.getLoanByMobileNumber(correlationId, mobileNumber).getBody();

        CardDTO cardDTO = cardsFeignClient.getCardByMobileNumber(correlationId, mobileNumber).getBody();

        customerDTO.setAccountDTO(accountDTO);
        customerInfoDTO.setCustomerDTO(customerDTO);
        customerInfoDTO.setLoanDTO(loanDTO);
        customerInfoDTO.setCardDTO(cardDTO);

        return  customerInfoDTO;
    }

}
