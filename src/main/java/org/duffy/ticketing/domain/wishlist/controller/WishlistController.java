package org.duffy.ticketing.domain.wishlist.controller;

import lombok.RequiredArgsConstructor;
import org.duffy.ticketing.domain.account.BuyerAccount;
import org.duffy.ticketing.domain.account.repository.AccountRepository;
import org.duffy.ticketing.domain.wishlist.service.dto.ReserveConcertRequest;
import org.duffy.ticketing.domain.wishlist.service.WishlistService;
import org.duffy.ticketing.domain.wishlist.service.dto.WishlistResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishlistController {
    private final WishlistService wishlistService;
    private final AccountRepository accountRepository;

    @GetMapping
    public ResponseEntity<List<WishlistResponse>> getWishList() {
        // TODO: JWT를 이용한 사용자 조회로 변경
        BuyerAccount buyer = (BuyerAccount) accountRepository.findById(3L).get();
        return ResponseEntity.ok(wishlistService.getWishlist(buyer));
    }

    @PostMapping
    public ResponseEntity<Boolean> addToWishlist(@RequestBody ReserveConcertRequest concertRequest) {
        // TODO: JWT를 이용한 사용자 조회로 변경
        BuyerAccount buyer = (BuyerAccount) accountRepository.findById(3L).get();
        wishlistService.addToWishlist(concertRequest, buyer);
        return ResponseEntity.ok(true);
    }
}
