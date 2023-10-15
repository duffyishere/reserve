package org.duffy.reserve.domain.account.service;

import lombok.RequiredArgsConstructor;
import org.duffy.reserve.domain.account.BuyerAccount;
import org.duffy.reserve.domain.account.SellerAccount;
import org.duffy.reserve.domain.account.dto.CreateBuyerAccountRequest;
import org.duffy.reserve.domain.account.dto.CreateSellerRequest;
import org.duffy.reserve.domain.account.repository.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public void createSellerAccount(CreateSellerRequest request) {
        String encryptedPassword = encryptionPassword(request.getPassword());
        SellerAccount seller = new SellerAccount(request, encryptedPassword);
        accountRepository.save(seller);
    }

    public void createBuyerAccount(CreateBuyerAccountRequest request) {
        String encryptedPassword = encryptionPassword(request.getPassword());
        BuyerAccount buyer = new BuyerAccount(request, encryptedPassword);
        accountRepository.save(buyer);
    }

    private String encryptionPassword(String original) {
        return passwordEncoder.encode(original);
    }
}
