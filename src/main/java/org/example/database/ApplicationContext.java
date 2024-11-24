package org.example.database;

import org.example.repository.UserRepository;
import org.example.repository.UserRepositoryImpl;
import org.example.service.UserService;
import org.example.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class ApplicationContext {
    private static final Connection CONNECTION;
    private static final UserRepository USERREPOSITORY;
    private static final UserService USERSERVICE;

    static {

        CONNECTION = Datasource.getConnection();
        try {
            USERREPOSITORY = new UserRepositoryImpl(CONNECTION);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        USERSERVICE = new UserServiceImpl(USERREPOSITORY);

    }

    public static UserService getUserService() {
        return USERSERVICE;
    }



}
