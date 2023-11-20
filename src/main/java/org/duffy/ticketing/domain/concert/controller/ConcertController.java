package org.duffy.ticketing.domain.concert.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.duffy.ticketing.domain.account.BuyerAccount;
import org.duffy.ticketing.domain.account.repository.AccountRepository;
import org.duffy.ticketing.domain.concert.dto.GetConcertDetailResponse;
import org.duffy.ticketing.domain.concert.dto.ReserveConcertRequest;
import org.duffy.ticketing.domain.concert.dto.SeatResponse;
import org.duffy.ticketing.domain.concert.service.ConcertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/concert")
@RequiredArgsConstructor
public class ConcertController {
    private final ConcertService concertService;
    private final AccountRepository accountRepository;

    /**
     * Retrieves the concert details by the provided ID.
     *
     * @param id The ID of the concert to retrieve.
     * @return The ResponseEntity containing the GetConcertDetailResponse object with the concert details.
     */
    @GetMapping("/{id}")
    public ResponseEntity<GetConcertDetailResponse> getConcertBy(@PathVariable("id") Long id) {
        return ResponseEntity.status(200).body(concertService.getConcertDetail(id));
    }

    /**
     * Retrieves the list of seats for a given concert ID.
     *
     * @param id The ID of the concert.
     * @return A ResponseEntity containing a list of SeatResponse objects representing the seats for the concert.
     */
    @GetMapping("/seat/{id}")
    public ResponseEntity<List<SeatResponse>> getSeatBy(@PathVariable("id") Long id) {
        return ResponseEntity.status(200).body(concertService.getSeatsFor(id));
    }

    /**
     * Reserves a concert for a buyer.
     *
     * @param body The request object containing the buyer's information and the concert details to reserve.
     * @return True if the concert was successfully reserved, false otherwise.
     */
    @PostMapping("/reserve")
    @Transactional
    public ResponseEntity<Boolean> reserveConcert(@RequestBody ReserveConcertRequest body) {
        // TODO: JWT를 이용한 사용자 조회로 변경
        BuyerAccount buyer = (BuyerAccount) accountRepository.findById(3L).get();
        concertService.reserveConcert(buyer, body);
        return ResponseEntity.ok(true);
    }
}
