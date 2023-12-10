package org.duffy.ticketing.domain.wishlist.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.duffy.ticketing.domain.account.BuyerAccount;
import org.duffy.ticketing.domain.account.repository.AccountRepository;
import org.duffy.ticketing.domain.concert.dto.ReserveConcertRequest;
import org.duffy.ticketing.domain.wishlist.service.WishlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishlistController {
    private final WishlistService wishlistService;
    private final AccountRepository accountRepository;

    /**
     * Reserves a concert for a buyer.
     *
     * @param concertRequest The request object containing the buyer's information and the concert details to reserve.
     * @return True if the concert was successfully reserved, false otherwise.
     */
    @PostMapping
    public ResponseEntity<Boolean> addToWishlist(@RequestBody ReserveConcertRequest concertRequest) {
        // TODO: JWT를 이용한 사용자 조회로 변경
        BuyerAccount buyer = (BuyerAccount) accountRepository.findById(3L).get();
        wishlistService.addToWishlist(concertRequest, buyer);
        return ResponseEntity.ok(true);
    }

}
