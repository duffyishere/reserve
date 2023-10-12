package org.duffy.reserve.domain.account.service;

import org.duffy.reserve.domain.account.dto.CreateSellerRequest;
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
        CreateSellerRequest request = CreateSellerRequest.builder()
                .email("duffy@icloud.com")
                .name("Junho")
                .password("test1234!")
                .build();

        accountService.createSellerAccount(request);
    }
}