package org.duffy.reserve.domain.concert.dto;

import lombok.Data;
import org.duffy.reserve.domain.concert.Seat;

@Data
public class SeatResponse {
    private Long id;
    private int seatNumber;
    private boolean isReservation;

    public SeatResponse(Seat seat) {
        this.id = seat.getId();
        this.seatNumber = seat.getSeatNumber();
        this.isReservation = seat.isReservation();
    }
}
