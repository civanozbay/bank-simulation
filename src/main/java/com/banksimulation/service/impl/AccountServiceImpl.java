package com.banksimulation.service.impl;

import com.banksimulation.enums.AccountStatus;
import com.banksimulation.enums.AccountType;
import com.banksimulation.model.Account;
import com.banksimulation.repository.AccountRepository;
import com.banksimulation.service.AccountService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createNewAccount(BigDecimal balance, Date creationDate, AccountType accountType, Long userId) {
        Account account = Account.builder().id(UUID.randomUUID())
                .userId(userId).accountType(accountType).
                balance(balance).creationDate(new Date()).accountStatus(AccountStatus.ACTIVE).build();
        return accountRepository.save(account);
    }

    @Override
    public List<Account> listAllAccount() {
        return accountRepository.findAll();
    }

    @Override
    public void deleteAccount(UUID id) {
        accountRepository.findById(id).setAccountStatus(AccountStatus.DELETED);

    }

    @Override
    public Account retrieveById(UUID id) {
        return accountRepository.findById(id);
    }
}
