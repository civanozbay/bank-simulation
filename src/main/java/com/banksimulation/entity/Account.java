package com.banksimulation.entity;

import com.banksimulation.enums.AccountStatus;
import com.banksimulation.enums.AccountType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal balance;

    @Column(columnDefinition = "DATE")
    private Date creationDate;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    private Long userId;

    public Account(BigDecimal balance, AccountType accountType, Date creationDate, AccountStatus accountStatus,Long userId) {
        this.balance = balance;
        this.accountType = accountType;
        this.creationDate = creationDate;
        this.accountStatus = accountStatus;
        this.userId = userId;
    }
}
