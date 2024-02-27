package com.banksimulation.repository;

import com.banksimulation.exception.RecordNotFoundException;
import com.banksimulation.model.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class AccountRepository {

    public static List<Account> accountList = new ArrayList<>();

    public Account save(Account account) {
        accountList.add(account);
        return account;
    }

    public List<Account> findAll() {
        return accountList;
    }

    public Account findById(UUID id) {
        return accountList.stream().
                filter(account -> account.getId().equals(id)).
                findAny().
                orElseThrow(() -> new RecordNotFoundException("account not exist in database"));

    }
}
