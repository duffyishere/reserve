package org.duffy.ticketing.domain.wishlist.service.dto;

import org.duffy.ticketing.domain.concert.dto.ConcertSummaryResponse;
import org.duffy.ticketing.domain.concert.dto.SeatResponse;
import org.duffy.ticketing.domain.wishlist.SeatWishlist;
import org.duffy.ticketing.global.Utils;

import java.util.List;
import java.util.stream.Collectors;

public record WishlistResponse(long id, ConcertSummaryResponse concert, List<SeatResponse> seats, String paymentDeadline) {
    public WishlistResponse(SeatWishlist wishlist) {
        this(wishlist.getId(),
                new ConcertSummaryResponse(wishlist.getConcert()),
                wishlist.getSeats().stream()
                        .map(SeatResponse::new)
                        .collect(Collectors.toList()),
                Utils.dateTimeToString(wishlist.getPaymentDeadline()));
    }
}
