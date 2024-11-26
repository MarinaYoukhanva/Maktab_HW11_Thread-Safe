package org.example.service.impl;

import org.example.base.service.BaseServiceImpl;
import org.example.database.ApplicationContext;
import org.example.entity.Transaction;
import org.example.entity.TransactionType;
import org.example.entity.Wallet;
import org.example.exception.NotEnoughCreditException;
import org.example.exception.WalletNotFoundException;
import org.example.repository.TransactionRepository;
import org.example.service.TransactionService;
import org.example.service.WalletService;

import java.util.Optional;

public class TransactionServiceImpl extends BaseServiceImpl<Integer, Transaction, TransactionRepository>
        implements TransactionService {

    WalletService walletService = ApplicationContext.getWalletService();

    public TransactionServiceImpl(TransactionRepository repository) {
        super(repository);
    }

    @Override
    public void charge(Wallet wallet, double amount) {
        synchronized (wallet) {
            Optional<Wallet> walletIfExists = walletService.findById(wallet.getId());
            if (walletIfExists.isEmpty())
                throw new WalletNotFoundException();
            wallet.setBalance(wallet.getBalance() + amount);
            walletService.update(wallet);
            Transaction transaction = new Transaction(amount, TransactionType.DEPOSIT, wallet);
            save(transaction);
        }
    }

    @Override
    public void withdraw(Wallet wallet, double amount) {
        synchronized (wallet) {
            if (wallet.getBalance() < amount)
                throw new NotEnoughCreditException();
            wallet.setBalance(wallet.getBalance() - amount);
            walletService.update(wallet);
            Transaction transaction = new Transaction(amount, TransactionType.WITHDRAW, wallet);
            save(transaction);
        }
    }
}
