package org.duffy.ticketing.domain.auth;

import org.duffy.ticketing.domain.auth.dto.AuthenticationResponse;
import org.duffy.ticketing.domain.auth.dto.RegisterBuyerAccountRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthenticationServiceTests {

    @Autowired
    public AuthenticationService service;

    @Test
    public void 고객용_계정_생성() {
        AuthenticationResponse response = service.registerBuyerAccount(new RegisterBuyerAccountRequest("buyer@gmail.com", "password", "buyer1"));
        System.out.println(response);
    }
}