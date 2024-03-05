package com.banksimulation.repository;

import com.banksimulation.entity.Account;
import com.banksimulation.exception.RecordNotFoundException;
import com.banksimulation.dto.AccountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public interface AccountRepository extends JpaRepository<Account,Long> {

}
