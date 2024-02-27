package com.banksimulation.service.impl;

import com.banksimulation.enums.AccountType;
import com.banksimulation.exception.AccountOwnerShipException;
import com.banksimulation.exception.BadRequestException;
import com.banksimulation.exception.RecordNotFoundException;
import com.banksimulation.model.Account;
import com.banksimulation.model.Transaction;
import com.banksimulation.repository.AccountRepository;
import com.banksimulation.service.TransactionService;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

public class TransactionServiceImpl implements TransactionService {


    AccountRepository accountRepository;

    @Override
    public Transaction makeTransfer(Account sender, Account receiver, BigDecimal amount, Date creationDate, String message) {
        validateAccount(sender,receiver);
        checkAccountOwnership(sender,receiver);

        return null;
    }

    private void checkAccountOwnership(Account sender, Account receiver) {
        if(sender.getAccountType().equals(AccountType.SAVING)
                ||receiver.getAccountType().equals(AccountType.SAVING) && !sender.getUserId().equals(receiver.getUserId())){
            throw new AccountOwnerShipException("If one of the account is saving,userId must be the same");
        }

    }

    private void validateAccount(Account sender, Account receiver) {
        // if any of the account is null or if account ids are the same or if the account exist in the db

        if(sender==null || receiver==null){
            throw new BadRequestException("Sender or receiver cannot be null");
        }

        if(sender.getId().equals(receiver.getId())){
            throw new BadRequestException("Sender account needs to be different than receiver");
        }

        findAccountById(sender.getId());
        findAccountById(receiver.getId());
    }

    private void findAccountById(UUID id) {
        accountRepository.findById(id);
    }

    @Override
    public List<Transaction> findAllTransaction() {
        return null;
    }
}
