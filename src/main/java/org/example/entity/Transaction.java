package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.base.model.BaseEntity;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends BaseEntity<Integer> {

    private double amount;
    private TransactionType type;
    private Wallet wallet;

    public Transaction(Integer integer, LocalDateTime createdAt, double amount, TransactionType type, Wallet wallet) {
        super(integer, createdAt);
        this.amount = amount;
        this.type = type;
        this.wallet = wallet;
    }
}
