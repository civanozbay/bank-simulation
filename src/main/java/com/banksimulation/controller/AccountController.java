package com.banksimulation.controller;

import com.banksimulation.dto.AccountDTO;
import com.banksimulation.enums.AccountType;
import com.banksimulation.service.AccountService;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;


@Controller
public class AccountController {

    private final AccountService accountService;


    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/index")
    public String getIndex(Model model){
        model.addAttribute("accountList",accountService.listAllAccount());
        return "account/index";
    }

    @GetMapping("/create-form")
    public String getCreateForm(Model model){
        model.addAttribute("account", AccountDTO.builder().build());
        model.addAttribute("accountTypes", AccountType.values());
        return "account/create-account";
    }

    @PostMapping("/create")
    public String createAccount(@Valid @ModelAttribute("account") AccountDTO accountDTO){
        accountService.createNewAccount(accountDTO.getBalance(), accountDTO.getCreationDate(), accountDTO.getAccountType(), accountDTO.getUserId());
        System.out.println(accountService.listAllAccount());
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String getDeleteAccount(@PathVariable("id") UUID id){
        accountService.deleteAccount(id);
        return "redirect:/index";
    }

}
