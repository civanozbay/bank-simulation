package com.banksimulation.service;

import com.banksimulation.dto.AccountDTO;
import com.banksimulation.dto.TransactionDTO;

import java.math.BigDecimal;


import java.util.Date;
import java.util.List;

public interface TransactionService {

    void makeTransfer(AccountDTO sender, AccountDTO receiver, BigDecimal amount, Date creationDate, String message);

    List<TransactionDTO> findAllTransaction();


    List<TransactionDTO> findTransactionListById(Long id);

    List<TransactionDTO> lastTransactions();
}
