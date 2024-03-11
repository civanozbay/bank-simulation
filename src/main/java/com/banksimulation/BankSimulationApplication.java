package com.banksimulation;

import com.banksimulation.dto.AccountDTO;
import com.banksimulation.enums.AccountType;
import com.banksimulation.service.AccountService;
import com.banksimulation.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.ModelMap;


import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class BankSimulationApplication {

	public static void main(String[] args) {

		ApplicationContext container = SpringApplication.run(BankSimulationApplication.class, args);

	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}


	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
