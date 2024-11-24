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
public class User extends BaseEntity<Integer> {

    private String username;
    private String password;

    public User(Integer id, LocalDateTime createdAt, String username, String password) {
        super(id, createdAt);
        this.username = username;
        this.password = password;
    }
    public User(Integer id, String username, String password) {
        super(id);
        this.username = username;
        this.password = password;
    }

}
