package org.duffy.ticketing.domain.concert.service;

import jakarta.transaction.Transactional;
import org.duffy.ticketing.domain.account.SellerAccount;
import org.duffy.ticketing.domain.account.repository.AccountRepository;
import org.duffy.ticketing.domain.concert.dto.CreateConcertRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;

@SpringBootTest
public class ConcertServiceTests {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ConcertService concertService;

    @Test
    @Transactional
    @Rollback(value = false)
    public void createConcertTest() {
        SellerAccount seller = (SellerAccount) accountRepository.findById(1L).get();

        CreateConcertRequest request = new CreateConcertRequest("테스트용 공연3",
                "해당 공연은 테스트용입니다.",
                "https://google.com",
                LocalDateTime.now(),
                LocalDateTime.now().plusMonths(1),
                1000);

        concertService.createConcert(seller, request);
    }
}