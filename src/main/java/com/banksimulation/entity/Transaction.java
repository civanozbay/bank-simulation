package com.banksimulation.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Account sender ;

    @ManyToOne
    private Account receiver;
    private BigDecimal amount;
    private String message;
    @Column(columnDefinition = "TIMESTAMP")
    private Date creationDate;




    public Transaction(BigDecimal amount, String message, Date creationDate) {
        this.amount = amount;
        this.message = message;
        this.creationDate = creationDate;
    }
}
