package org.example;

import org.example.entity.Wallet;
import org.example.service.TransactionService;

import java.util.Optional;

public class ChargeTask implements Runnable {

    int threadNumber;
    Wallet wallet;
    TransactionService transactionService;
    public ChargeTask(int threadNumber, Wallet wallet, TransactionService transactionService) {
        this.threadNumber = threadNumber;
        this.wallet = wallet;
        this.transactionService = transactionService;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            transactionService.charge(wallet, 10);
            System.out.println("thread number:"
                    + (threadNumber+1) + " +++ charge number: "
                    + (i+1)+ "    balance: " + wallet.getBalance());
        }
    }
}
