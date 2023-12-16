package org.duffy.ticketing.domain.wishlist.controller;

import lombok.RequiredArgsConstructor;
import org.duffy.ticketing.domain.account.Account;
import org.duffy.ticketing.domain.account.BuyerAccount;
import org.duffy.ticketing.domain.account.repository.AccountRepository;
import org.duffy.ticketing.domain.wishlist.dto.ReserveConcertRequest;
import org.duffy.ticketing.domain.wishlist.service.WishlistService;
import org.duffy.ticketing.domain.wishlist.dto.WishlistResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishlistController {
    private final WishlistService wishlistService;

    @GetMapping
    public ResponseEntity<List<WishlistResponse>> getWishList(@AuthenticationPrincipal Account account) {
        return ResponseEntity.ok(wishlistService.getWishlist((BuyerAccount) account));
    }

    @PostMapping
    public ResponseEntity<Boolean> addToWishlist(@AuthenticationPrincipal Account account,
                                                 @RequestBody ReserveConcertRequest concertRequest) {
        wishlistService.addToWishlist(concertRequest, (BuyerAccount) account);
        return ResponseEntity.ok(true);
    }
}
