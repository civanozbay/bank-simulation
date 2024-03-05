package com.banksimulation.service.impl;

import com.banksimulation.dto.AccountDTO;
import com.banksimulation.entity.Account;
import com.banksimulation.enums.AccountStatus;
import com.banksimulation.mapper.AccountMapper;
import com.banksimulation.repository.AccountRepository;
import com.banksimulation.service.AccountService;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;
    AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository,AccountMapper accountMapper){
        this.accountRepository = accountRepository;
        this.accountMapper=accountMapper;
    }

    @Override
    public void createNewAccount(AccountDTO accountDTO) {
        accountDTO.setAccountStatus(AccountStatus.ACTIVE);
        accountDTO.setCreationDate(new Date());
        Account account = accountMapper.convertToEntity(accountDTO);
        accountRepository.save(account);
    }

    @Override
    public List<AccountDTO> listAllAccount()
    {
        List<Account> accountList = accountRepository.findAll();
        return accountList.stream().map(account -> accountMapper.convertToDTO(account)).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).get();
        account.setAccountStatus(AccountStatus.DELETED);
        accountRepository.save(account);
    }

    @Override
    public AccountDTO retrieveById(Long id)
    {
        return accountMapper.convertToDTO(accountRepository.findById(id).get());
    }

    @Override
    public List<AccountDTO> listAllActiveAccounts() {
        return accountRepository.findAllByAccountStatus(AccountStatus.ACTIVE).stream()
                .map(account -> accountMapper.convertToDTO(account)).collect(Collectors.toList());
    }
}
