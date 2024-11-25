package org.example.service.impl;

import org.example.base.service.BaseServiceImpl;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;

public class UserServiceImpl extends BaseServiceImpl<Integer, User, UserRepository>
        implements UserService {


    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }
}
