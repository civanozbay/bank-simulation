package com.banksimulation.controller;

import com.banksimulation.model.Account;
import com.banksimulation.model.Transaction;
import com.banksimulation.service.AccountService;
import com.banksimulation.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@Controller
public class TransactionController {

    AccountService accountService;
    TransactionService transactionService;

    public TransactionController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @GetMapping("/make-transfer")
    public String makeTransfer(Model model){
        model.addAttribute("accounts", accountService.listAllAccount());
        model.addAttribute("transaction", Transaction.builder().build());
        model.addAttribute("lastTransactions", transactionService.findAllTransaction());
        return "transaction/make-transfer";
    }

    @PostMapping("/transfer")
    public String transfer(@ModelAttribute("transaction") Transaction transaction,Model model){

        Account sender = accountService.retrieveById(transaction.getSender());
        Account receiver = accountService.retrieveById(transaction.getReceiver());
        transactionService.makeTransfer(sender,receiver,transaction.getAmount(),new Date(),transaction.getMessage());

        return "redirect:/make-transfer";
    }

    @GetMapping("/account-transaction/{id}")
    public String accountTransaction(@PathVariable("id")UUID id,Model model){

        model.addAttribute("transactions",transactionService.findTransactionListById(id));

        return "transaction/transactions";
    }
}
