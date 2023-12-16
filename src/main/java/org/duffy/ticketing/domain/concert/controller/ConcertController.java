package org.duffy.ticketing.domain.concert.controller;

import lombok.RequiredArgsConstructor;
import org.duffy.ticketing.domain.account.Account;
import org.duffy.ticketing.domain.account.SellerAccount;
import org.duffy.ticketing.domain.concert.dto.CreateConcertRequest;
import org.duffy.ticketing.domain.concert.dto.GetConcertDetailResponse;
import org.duffy.ticketing.domain.concert.dto.SeatResponse;
import org.duffy.ticketing.domain.concert.service.ConcertService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/concert")
@RequiredArgsConstructor
public class ConcertController {
    private final ConcertService concertService;

    @GetMapping("/{id}")
    public ResponseEntity<GetConcertDetailResponse> getConcertBy(@PathVariable("id") Long id) {
        return ResponseEntity.ok(concertService.getConcertDetail(id));
    }

    @GetMapping("/seat/{id}")
    public ResponseEntity<List<SeatResponse>> getSeatBy(@PathVariable("id") Long id) {
        return ResponseEntity.ok(concertService.getSeatsFor(id));
    }

    @PreAuthorize("hasRole('SELLER')")
    @PostMapping
    public ResponseEntity<Boolean> createConcert(@AuthenticationPrincipal Account account,
                                                 @RequestBody CreateConcertRequest request) {
        concertService.createConcert((SellerAccount) account, request);
        return ResponseEntity.ok(true);
    }
}
