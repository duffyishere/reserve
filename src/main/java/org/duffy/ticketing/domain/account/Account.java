package org.duffy.ticketing.domain.account;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.duffy.ticketing.domain.account.dto.CreateAccountRequest;
import org.duffy.ticketing.domain.base.BaseTimeEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Getter
@Inheritance
@DiscriminatorColumn
@NoArgsConstructor
public class Account extends BaseTimeEntity implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String password;
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Account(CreateAccountRequest request, String encryptedPassword) {
        this.email = request.getEmail();
        this.name = request.getName();
        this.password = encryptedPassword;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
