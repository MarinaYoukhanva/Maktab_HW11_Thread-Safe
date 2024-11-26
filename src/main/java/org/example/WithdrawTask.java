package org.example;

import org.example.entity.Wallet;
import org.example.exception.NotEnoughCreditException;
import org.example.service.TransactionService;

import java.util.Optional;

public class WithdrawTask implements Runnable {

    int threadNumber;
    Wallet wallet;
    TransactionService transactionService;
    public WithdrawTask(int threadNumber, Wallet wallet, TransactionService transactionService) {
        this.threadNumber = threadNumber;
        this.wallet = wallet;
        this.transactionService = transactionService;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                transactionService.withdraw(wallet, 10);
            } catch (NotEnoughCreditException e) {
                System.out.println(e.getMessage());
                continue;
            }
            System.out.println("thread number:" + (threadNumber+1)
                    + " --- withdraw number: " + (i+1)
                    + "    balance: " + wallet.getBalance());
        }
    }
}
