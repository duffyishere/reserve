package org.duffy.ticketing.domain.account;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.duffy.ticketing.domain.account.dto.CreateAccountRequest;
import org.duffy.ticketing.domain.base.BaseTimeEntity;

@Entity
@Getter
@Inheritance
@DiscriminatorColumn
@NoArgsConstructor
public class Account extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String password;
    private String name;

    public Account(CreateAccountRequest request, String encryptedPassword) {
        this.email = request.getEmail();
        this.name = request.getName();
        this.password = encryptedPassword;
    }
}
