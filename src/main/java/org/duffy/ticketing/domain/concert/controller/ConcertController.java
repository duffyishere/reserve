package org.duffy.ticketing.domain.concert.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.duffy.ticketing.domain.account.BuyerAccount;
import org.duffy.ticketing.domain.account.repository.AccountRepository;
import org.duffy.ticketing.domain.concert.dto.GetConcertDetailResponse;
import org.duffy.ticketing.domain.concert.dto.ReserveConcertRequest;
import org.duffy.ticketing.domain.concert.service.ConcertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/concert")
@RequiredArgsConstructor
public class ConcertController {
    private final ConcertService concertService;
    private final AccountRepository accountRepository;

    @GetMapping("/{id}")
    public ResponseEntity<GetConcertDetailResponse> getConcertBy(@PathVariable("id") Long id) {
        return ResponseEntity.status(200).body(concertService.getConcertDetail(id));
    }

    @PostMapping("/reserve")
    @Transactional
    public ResponseEntity<Boolean> reserveConcert(@RequestBody ReserveConcertRequest body) {
        // TODO: JWT를 이용한 사용자 조회로 변경
        BuyerAccount buyer = (BuyerAccount) accountRepository.findById(3L).get();
        concertService.reserveConcert(buyer, body);
        return ResponseEntity.ok(true);
    }
}
