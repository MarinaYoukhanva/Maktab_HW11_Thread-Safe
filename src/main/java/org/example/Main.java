package org.example;

import org.example.database.ApplicationContext;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.example.service.WalletService;

import java.sql.SQLException;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserService us = ApplicationContext.getUserService();
        WalletService ws = ApplicationContext.getWalletService();

//        User user1 = new User(0, null, "a", "b");
//        us.save(user1);
//        User user2 = new User( "c", "c");
//        us.save(user2);

        Optional<User> user1Update = us.findById(1);
//        if (user1Update.isPresent()) {
//            user1Update.get().setPassword("a");
//            us.update(user1Update.get());
//        }


    }
}