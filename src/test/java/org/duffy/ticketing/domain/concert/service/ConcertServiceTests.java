package org.duffy.ticketing.domain.concert.service;

import jakarta.transaction.Transactional;
import org.duffy.ticketing.domain.account.BuyerAccount;
import org.duffy.ticketing.domain.account.SellerAccount;
import org.duffy.ticketing.domain.account.repository.AccountRepository;
import org.duffy.ticketing.domain.concert.dto.CreateConcertRequest;
import org.duffy.ticketing.domain.concert.dto.ReserveConcertRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.List;

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

        CreateConcertRequest request = CreateConcertRequest.builder()
                .title("테스트용 공연3")
                .description("해당 공연은 테스트용입니다.")
                .thumbnailURL("https://google.com")
                .openDateTime(LocalDateTime.now())
                .closeDateTime(LocalDateTime.now().plusMonths(1))
                .seatingCapacity(1000)
                .build();

        concertService.createConcert(seller, request);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void createReservationTest() {
        BuyerAccount buyer = (BuyerAccount) accountRepository.findById(3L).get();

        ReserveConcertRequest concertRequest = ReserveConcertRequest.builder()
                .concertId(152L)
                .seatNumbers(List.of(1))
                .build();

        concertService.addToWishlist(concertRequest, buyer);
    }
}