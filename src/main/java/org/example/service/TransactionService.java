package org.example.service;

import org.example.base.service.BaseService;
import org.example.entity.Transaction;
import org.example.entity.Wallet;

import java.sql.SQLException;

public interface TransactionService extends BaseService<Integer, Transaction> {

    void charge (Wallet wallet, double amount);

    void withdraw (Wallet wallet, double amount);
}
