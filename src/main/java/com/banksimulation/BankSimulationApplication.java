package com.banksimulation;

import com.banksimulation.enums.AccountType;
import com.banksimulation.model.Account;
import com.banksimulation.service.AccountService;
import com.banksimulation.service.TransactionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class BankSimulationApplication {

	public static void main(String[] args) {

		ApplicationContext container = SpringApplication.run(BankSimulationApplication.class, args);

		AccountService accountService = container.getBean(AccountService.class);
		TransactionService transactionService = container.getBean(TransactionService.class);

		Account sender = accountService.createNewAccount(BigDecimal.valueOf(50), new Date(), AccountType.CHECKING, 1L);
		Account receiver = accountService.createNewAccount(BigDecimal.valueOf(50), new Date(), AccountType.SAVING, 1L);
		Account receiver2 = accountService.createNewAccount(BigDecimal.valueOf(100), new Date(), AccountType.SAVING, 2L);
		transactionService.makeTransfer(sender, receiver, BigDecimal.valueOf(40), new Date(), "Transaction 1");


	}

}
