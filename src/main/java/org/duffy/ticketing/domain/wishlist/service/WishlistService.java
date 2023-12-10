package org.duffy.ticketing.domain.wishlist.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.duffy.ticketing.domain.account.BuyerAccount;
import org.duffy.ticketing.domain.concert.Concert;
import org.duffy.ticketing.domain.concert.Seat;
import org.duffy.ticketing.domain.concert.dto.ReserveConcertRequest;
import org.duffy.ticketing.domain.concert.repository.CustomSeatRepository;
import org.duffy.ticketing.domain.concert.service.ConcertService;
import org.duffy.ticketing.domain.wishlist.SeatWishlist;
import org.duffy.ticketing.domain.wishlist.repository.SeatWishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistService {
    private final ConcertService concertService;
    private final CustomSeatRepository customSeatRepository;
    private final SeatWishlistRepository wishlistRepository;

    private static final long PAYMENT_PERIOD_IN_DAYS = 1;

    @Transactional
    public void addToWishlist(ReserveConcertRequest concertRequest, BuyerAccount buyer) {
        Concert concert = concertService.getConcertById(concertRequest.getConcertId());
        if (!concert.isOpenTimePassed()) {
            throw new IllegalArgumentException("It's not time to open the reservation yet.");
        }

        List<Seat> selectedSeats = getSeatsBySeatNumber(concert, concertRequest.getSeatNumbers());
        createSeatWishlist(buyer, concert, selectedSeats);
    }

    private List<Seat> getSeatsBySeatNumber(Concert concert, List<Integer> seatNumbers) {
        List<Seat> seats = customSeatRepository.findAllBySeatNumbers(seatNumbers, concert);
        if (seats.size() != seatNumbers.size())
            throw new IllegalArgumentException("The number of requested seats is not available");

        return seats;
    }

    private void createSeatWishlist(BuyerAccount buyer, Concert concert, List<Seat> selectedSeats) {
        SeatWishlist wishlist = new SeatWishlist(buyer, concert, selectedSeats, PAYMENT_PERIOD_IN_DAYS);
        selectedSeats.forEach(Seat::select);
        wishlistRepository.save(wishlist);

        concert.decreaseRemainSeatCount(selectedSeats.size());
    }
}
