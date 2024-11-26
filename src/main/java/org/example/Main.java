package org.example;

import org.example.database.ApplicationContext;
import org.example.entity.Wallet;
import org.example.service.TransactionService;
import org.example.service.UserService;
import org.example.service.WalletService;

import java.util.Optional;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        UserService userService = ApplicationContext.getUserService();
        WalletService walletService = ApplicationContext.getWalletService();
        TransactionService transactionService = ApplicationContext.getTransactionService();

        Optional<Wallet> wallet = walletService.findById(1);

        for (int i = 0; i < 5; i++) {
            Runnable runnableCharge = new ChargeTask(i, wallet.get(), transactionService);
            Runnable runnableWithdraw = new WithdrawTask(i, wallet.get(), transactionService);
            Thread chargeThread = new Thread(runnableCharge);
            Thread withdrawThread = new Thread(runnableWithdraw);
            chargeThread.start();
            withdrawThread.start();
        }
        Thread.sleep(5000);
        System.out.println("final balance: " + wallet.get().getBalance());

    }
}