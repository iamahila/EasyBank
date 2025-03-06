package com.bank.accounts.service;

import com.bank.accounts.dto.AccountDTO;
import com.bank.accounts.dto.CustomerDTO;
import com.bank.accounts.entity.AccountEntity;
import com.bank.accounts.entity.CustomerEntity;
import com.bank.accounts.exception.ResourceNotFoundException;
import com.bank.accounts.repository.AccountRepository;
import com.bank.accounts.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class AccountService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;


    public Optional<CustomerEntity> getCustomerData(String mobileNumber){
        return customerRepository.findByMobileNumber(mobileNumber);
    }

    public Optional<AccountEntity> getAccountData(Long customerId){
        return accountRepository.findByCustomerId(customerId);
    }

    public Optional<AccountEntity> findById(Long Id){
        return accountRepository.findById(Id);
    }

    public AccountEntity save(AccountEntity accountEntity){
        return accountRepository.save(accountEntity);
    }

    public CustomerEntity save(CustomerEntity customerEntity){
        return customerRepository.save(customerEntity);
    }

    public void delete(Long customerId){
        customerRepository.deleteById(customerId);
    }

    public void deleteByCustomerId(Long customerId){
        accountRepository.deleteByCustomerId(customerId);
    }

}
