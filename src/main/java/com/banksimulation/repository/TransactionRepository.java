package com.banksimulation.repository;

import com.banksimulation.dto.TransactionDTO;
import com.banksimulation.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction,Long> {


}
