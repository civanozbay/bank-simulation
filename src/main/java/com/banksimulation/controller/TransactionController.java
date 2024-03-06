package com.banksimulation.controller;

import com.banksimulation.dto.AccountDTO;
import com.banksimulation.dto.TransactionDTO;
import com.banksimulation.service.AccountService;
import com.banksimulation.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

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
        model.addAttribute("accounts", accountService.listAllActiveAccounts());
        model.addAttribute("transactionDTO", new TransactionDTO());
        model.addAttribute("lastTransactions", transactionService.findAllTransaction());
        return "transaction/make-transfer";
    }

    @PostMapping("/transfer")
    public String transfer(@ModelAttribute("transactionDTO") TransactionDTO transactionDTO, Model model){

        AccountDTO sender = accountService.retrieveById(transactionDTO.getSender().getId());
        AccountDTO receiver = accountService.retrieveById(transactionDTO.getReceiver().getId());
        transactionService.makeTransfer(sender,receiver, transactionDTO.getAmount(),new Date(), transactionDTO.getMessage());

        return "redirect:/make-transfer";
    }

    @GetMapping("/transaction/{id}")
    public String accountTransaction(@PathVariable("id") Long id, Model model){

        model.addAttribute("transactions",transactionService.findTransactionListById(id));

        return "transaction/transactions";
    }
}
