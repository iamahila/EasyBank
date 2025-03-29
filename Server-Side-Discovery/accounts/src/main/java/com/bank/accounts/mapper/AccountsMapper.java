package com.bank.accounts.mapper;

import com.bank.accounts.dto.AccountDTO;
import com.bank.accounts.entity.AccountEntity;
import lombok.*;

@Data
public class AccountsMapper {

    public static AccountDTO mapToAccountsDto(AccountEntity accounts, AccountDTO accountsDto) {
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        accountsDto.setCustomerId(accounts.getCustomerId());
        return accountsDto;
    }

    public static AccountEntity mapToAccounts(AccountDTO accountsDto, AccountEntity accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        accounts.setCustomerId(accountsDto.getCustomerId());
        return accounts;
    }

}
