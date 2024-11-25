package org.example.service.impl;

import org.example.base.service.BaseServiceImpl;
import org.example.entity.Wallet;
import org.example.repository.WalletRepository;
import org.example.service.WalletService;

public class WalletServiceImpl extends BaseServiceImpl<Integer, Wallet, WalletRepository>
        implements WalletService {

    public WalletServiceImpl(WalletRepository repository) {
        super(repository);
    }
}
