package com.banksimulation.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    private AccountDTO sender;
    private AccountDTO receiver;
    private BigDecimal amount;
    private String message;
    private Date creationDate;

}
