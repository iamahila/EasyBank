package com.bank.accounts.business;

import com.bank.accounts.dto.AccountDTO;
import com.bank.accounts.dto.CustomerDTO;
import com.bank.accounts.entity.AccountEntity;
import com.bank.accounts.entity.CustomerEntity;
import com.bank.accounts.exception.CustomerAlreadyExists;
import com.bank.accounts.exception.ResourceNotFoundException;
import com.bank.accounts.mapper.AccountsMapper;
import com.bank.accounts.mapper.CustomerMapper;
import com.bank.accounts.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class AccountBusiness {

    @Autowired
    private AccountService accountService;

    public void createAccount(CustomerDTO customerDTO){
        Optional<CustomerEntity> customerBeforeValidation =
                accountService.getCustomerData(customerDTO.getMobileNumber());

        if(customerBeforeValidation.isPresent()){
            throw new CustomerAlreadyExists("Mobile number already exists");
        }

        CustomerEntity customerEntity = CustomerMapper.mapToCustomer(customerDTO, new CustomerEntity());
        CustomerEntity customer = accountService.save(customerEntity);


        AccountDTO accountDTO =  createAccountEntity(customer);
        AccountEntity accountEntity = AccountsMapper.mapToAccounts(accountDTO, new AccountEntity());
        accountService.save(accountEntity);
    }

    public CustomerDTO getCustomerData(String mobileNumber){
        CustomerEntity customerEntity = accountService.getCustomerData(mobileNumber)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber)
                );

        AccountEntity accountEntity = accountService.getAccountData(customerEntity.getCustomerId())
                .orElseThrow(
                        ()-> new ResourceNotFoundException("account", "Customer Id", customerEntity.getCustomerId().toString())
                );

        AccountDTO accountDTO = AccountsMapper.mapToAccountsDto(accountEntity, new AccountDTO());
        CustomerDTO customerDTO = CustomerMapper.mapToCustomerDto(customerEntity, new CustomerDTO());
        customerDTO.setAccountDTO(accountDTO);

        return customerDTO;
    }

    public boolean updateData(CustomerDTO customerDTO){
        accountService.findById(customerDTO.getAccountDTO().getAccountNumber())
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Account", "AccountId", customerDTO.getAccountDTO().getAccountNumber().toString())
                );
        AccountEntity account = AccountsMapper.mapToAccounts(customerDTO.getAccountDTO(), new AccountEntity());
        accountService.save(account);

        CustomerEntity customer = CustomerMapper.mapToCustomer(customerDTO, new CustomerEntity());
        customer.setCustomerId(account.getCustomerId());
        accountService.save(customer);
        return true;
    }

    public boolean deleteData(String mobileNumber){
        CustomerEntity customerEntity = accountService.getCustomerData(mobileNumber)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Customer", "Mobile number", mobileNumber)
                );
        accountService.deleteByCustomerId(customerEntity.getCustomerId());
        accountService.delete(customerEntity.getCustomerId());
        return true;
    }

    private AccountDTO createAccountEntity(CustomerEntity customerEntity) {

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setCustomerId(customerEntity.getCustomerId());
        accountDTO.setAccountType("Savings");
        accountDTO.setBranchAddress("No.9, Chennai");
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        accountDTO.setAccountNumber(randomAccNumber);

        return accountDTO;

    }

}
