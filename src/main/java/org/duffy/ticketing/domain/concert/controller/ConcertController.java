package org.duffy.ticketing.domain.concert.controller;

import lombok.RequiredArgsConstructor;
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

    /**
     * Retrieves the concert details by the provided ID.
     *
     * @param id The ID of the concert to retrieve.
     * @return The ResponseEntity containing the GetConcertDetailResponse object with the concert details.
     */
    @GetMapping("/{id}")
    public ResponseEntity<GetConcertDetailResponse> getConcertBy(@PathVariable("id") Long id) {
        return ResponseEntity.ok(concertService.getConcertDetail(id));
    }

    /**
     * Retrieves the list of seats for a given concert ID.
     *
     * @param id The ID of the concert.
     * @return A ResponseEntity containing a list of SeatResponse objects representing the seats for the concert.
     */
    @GetMapping("/seat/{id}")
    public ResponseEntity<List<SeatResponse>> getSeatBy(@PathVariable("id") Long id) {
        return ResponseEntity.ok(concertService.getSeatsFor(id));
    }

    @PreAuthorize("hasRole('SELLER')")
    @PostMapping
    public ResponseEntity<Boolean> createConcert(@RequestBody CreateConcertRequest request, @AuthenticationPrincipal SellerAccount seller) {
        concertService.createConcert(seller, request);
        return ResponseEntity.ok(true);
    }
}
