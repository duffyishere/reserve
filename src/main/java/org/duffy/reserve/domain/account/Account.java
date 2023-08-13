package org.duffy.reserve.domain.account;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Inheritance
@DiscriminatorColumn
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String password;
    private String name;

    public Account() {
    }
}
