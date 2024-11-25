package org.example.entity;

import org.example.base.model.BaseEntity;

public class Transaction extends BaseEntity<Integer> {

    private double amount;
    private TransactionType type;
    private Wallet wallet;
}
