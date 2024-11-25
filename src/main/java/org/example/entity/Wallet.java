package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.base.model.BaseEntity;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Wallet extends BaseEntity<Integer> {

    private String address;
    private double balance;
    private User user;

    public Wallet(Integer integer, LocalDateTime createdAt, String address, double balance, User user) {
        super(integer, createdAt);
        this.address = address;
        this.balance = balance;
        this.user = user;
    }
}
