package com.banksimulation.service.impl;

import com.banksimulation.dto.AccountDTO;
import com.banksimulation.dto.TransactionDTO;
import com.banksimulation.enums.AccountType;
import com.banksimulation.exception.AccountOwnerShipException;
import com.banksimulation.exception.BadRequestException;
import com.banksimulation.exception.BalanceNotSufficientException;
import com.banksimulation.repository.AccountRepository;
import com.banksimulation.repository.TransactionRepository;
import com.banksimulation.service.TransactionService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TransactionServiceImpl implements TransactionService {


    AccountRepository accountRepository;
    TransactionRepository transactionRepository;

    public TransactionServiceImpl(AccountRepository accountRepository,TransactionRepository transactionRepository){
        this.transactionRepository=transactionRepository;
        this.accountRepository=accountRepository;
    }


    private TransactionDTO createTransaction(AccountDTO sender, AccountDTO receiver, BigDecimal amount, Date creationDate, String message) {
        TransactionDTO transactionDTO = new TransactionDTO();
        return transactionRepository.save(transactionDTO);
    }

    private void executeBalanceAndUpdateIfRequired(BigDecimal amount, AccountDTO sender, AccountDTO receiver) {
        if(checkSenderBalance(sender,amount)){
            sender.setBalance((sender.getBalance().subtract(amount)));
            receiver.setBalance(receiver.getBalance().add(amount));
        }else{
            throw new BalanceNotSufficientException("Balance is not enough for this transfer");
        }
    }

    private boolean checkSenderBalance(AccountDTO sender, BigDecimal amount) {
        return sender.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) >= 0;
    }

    private void checkAccountOwnership(AccountDTO sender, AccountDTO receiver) {
        if((sender.getAccountType().equals(AccountType.SAVING)
                ||receiver.getAccountType().equals(AccountType.SAVING))&& !sender.getUserId().equals(receiver.getUserId()))
        {
            throw new AccountOwnerShipException("If one of the account is saving, userId must be the same");
        }

    }

    private void validateAccount(AccountDTO sender, AccountDTO receiver) {
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

    private void findAccountById(Long id) {
        accountRepository.findById(id);
    }


    @Override
    public TransactionDTO makeTransfer(AccountDTO sender, AccountDTO receiver, BigDecimal amount, Date creationDate, String message) {
        validateAccount(sender,receiver);
        checkAccountOwnership(sender,receiver);
        executeBalanceAndUpdateIfRequired(amount,sender,receiver);

        return createTransaction(sender,receiver,amount,creationDate,message);
    }

    @Override
    public List<TransactionDTO> findAllTransaction() {
        return transactionRepository.findAll();
    }

    @Override
    public List<TransactionDTO> findTransactionListById(Long id) {
        return transactionRepository.transactionDTOList.stream().
            filter(transactionDTO -> transactionDTO.getSender().equals(id) || transactionDTO.getReceiver().equals(id)).
                collect(Collectors.toList());
    }

}
