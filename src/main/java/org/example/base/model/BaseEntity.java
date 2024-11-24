package org.example.base.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity<ID extends Serializable> {
    private ID id;
    private LocalDateTime createdAt;

    public BaseEntity(Integer id) {
    }
}
