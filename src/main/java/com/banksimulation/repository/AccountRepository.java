package com.banksimulation.repository;

import com.banksimulation.exception.RecordNotFoundException;
import com.banksimulation.dto.AccountDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AccountRepository {

    public static List<AccountDTO> accountDTOList = new ArrayList<>();

    public AccountDTO save(AccountDTO accountDTO) {
        accountDTOList.add(accountDTO);
        return accountDTO;
    }

    public List<AccountDTO> findAll() {
        return accountDTOList;
    }

    public AccountDTO findById(Long id) {
        return accountDTOList.stream().
                filter(account -> account.getId().equals(id)).
                findAny().
                orElseThrow(() -> new RecordNotFoundException("account not exist in database"));

    }
}
