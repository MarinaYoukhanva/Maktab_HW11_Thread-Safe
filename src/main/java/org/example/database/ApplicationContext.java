package org.example.database;

import org.example.repository.TransactionRepository;
import org.example.repository.UserRepository;
import org.example.repository.WalletRepository;
import org.example.repository.impl.TransactionRepositoryImpl;
import org.example.repository.impl.UserRepositoryImpl;
import org.example.repository.impl.WalletRepositoryImpl;
import org.example.service.TransactionService;
import org.example.service.UserService;
import org.example.service.WalletService;
import org.example.service.impl.TransactionServiceImpl;
import org.example.service.impl.UserServiceImpl;
import org.example.service.impl.WalletServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class ApplicationContext {
    private static final Connection CONNECTION;

    private static final UserRepository USERREPOSITORY;
    private static final WalletRepository WALLETREPOSITORY;
    private static final TransactionRepository TRANSACTIONREPOSITORY;

    private static final UserService USERSERVICE;
    private static final WalletService WALLETSERVICE;
    private static final TransactionService TRANSACTIONSERVICE;


    static {
        CONNECTION = Datasource.getConnection();
        try {
            USERREPOSITORY = new UserRepositoryImpl(CONNECTION);
            WALLETREPOSITORY = new WalletRepositoryImpl(CONNECTION);
            TRANSACTIONREPOSITORY = new TransactionRepositoryImpl(CONNECTION);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        USERSERVICE = new UserServiceImpl(USERREPOSITORY);
        WALLETSERVICE = new WalletServiceImpl(WALLETREPOSITORY);
        TRANSACTIONSERVICE = new TransactionServiceImpl(TRANSACTIONREPOSITORY);

    }

    public static UserService getUserService() {
        return USERSERVICE;
    }

    public static WalletService getWalletService() {
        return WALLETSERVICE;
    }

    public static TransactionService getTransactionService() {
        return TRANSACTIONSERVICE;
    }

}
