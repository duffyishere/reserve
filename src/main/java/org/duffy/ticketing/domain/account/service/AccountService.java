package org.duffy.ticketing.domain.account.service;

import lombok.RequiredArgsConstructor;
import org.duffy.ticketing.domain.account.Account;
import org.duffy.ticketing.domain.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Account getAccountBy(String email) {
        return accountRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Account with email " + email + " not found"));
    }

    public void save(Account account) {
        accountRepository.save(account);
    }
}
