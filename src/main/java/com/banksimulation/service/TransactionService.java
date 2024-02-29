package com.banksimulation.service;

import com.banksimulation.model.Account;
import com.banksimulation.model.Transaction;
import java.math.BigDecimal;


import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface TransactionService {

    Transaction makeTransfer(Account sender, Account receiver, BigDecimal amount, Date creationDate, String message);

    List<Transaction> findAllTransaction();


    List<Transaction> findTransactionListById(UUID id);
}
