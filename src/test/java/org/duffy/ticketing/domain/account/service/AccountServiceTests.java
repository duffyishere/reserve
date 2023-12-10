package org.duffy.ticketing.domain.account.service;

import org.duffy.ticketing.domain.auth.dto.RegisterBuyerAccountRequest;
import org.duffy.ticketing.domain.auth.dto.RegisterSellerAccountRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
public class AccountServiceTests {

    @Autowired
    AccountService accountService;

    @Test
    @Rollback(value = false)
    public void createSellerAccountTest() {
        RegisterSellerAccountRequest request = RegisterSellerAccountRequest.builder()
                .email("duffy@icloud.com")
                .name("Junho")
                .password("test1234!")
                .build();

        accountService.createSellerAccount(request);
    }

    @Test
    @Rollback(value = false)
    public void createBuyerAccountTest() {
        RegisterBuyerAccountRequest request = RegisterBuyerAccountRequest.builder()
                .email("seller@icloud.com")
                .name("seller")
                .password("test1234!")
                .build();
        accountService.createBuyerAccount(request);
    }
}