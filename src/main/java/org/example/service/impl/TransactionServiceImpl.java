package org.example.service.impl;

import org.example.base.service.BaseServiceImpl;
import org.example.entity.Transaction;
import org.example.repository.TransactionRepository;
import org.example.service.TransactionService;

public class TransactionServiceImpl extends BaseServiceImpl<Integer, Transaction, TransactionRepository>
        implements TransactionService {

    public TransactionServiceImpl(TransactionRepository repository) {
        super(repository);
    }
}
