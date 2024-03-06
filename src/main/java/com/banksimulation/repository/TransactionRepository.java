package com.banksimulation.repository;

import com.banksimulation.dto.TransactionDTO;
import com.banksimulation.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction,Long> {


    @Query(value = "SELECT * FROM transactions order by creation_date desc limit 10",nativeQuery = true)
    List<Transaction> findLastTenTransaction();

    @Query("SELECT t from Transaction t where t.sender.id=?1 or t.receiver.id=?1")
    List<Transaction> findTransactionListById(Long id);
}
