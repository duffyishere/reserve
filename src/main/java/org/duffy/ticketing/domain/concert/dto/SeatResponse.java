package org.duffy.ticketing.domain.concert.dto;

import org.duffy.ticketing.domain.concert.Seat;

public record SeatResponse(Long id, int seatNumber, boolean isReservation) {
    public SeatResponse(Seat seat) {
        this(seat.getId(), seat.getSeatNumber(), seat.isAddedToWishlist());
    }
}