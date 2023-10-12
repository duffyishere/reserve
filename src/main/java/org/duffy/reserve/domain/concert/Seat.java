package org.duffy.reserve.domain.concert;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Seat {
    @Id
    @GeneratedValue
    private Long id;
    private int seatNumber;

    @ManyToOne
    private Concert concert;

    private boolean isReservation;
    private LocalDateTime paymentDeadline;

    private boolean isPaid;

    public Seat(int seatNumber, Concert concert) {
        this.seatNumber = seatNumber;
        setConcert(concert);
    }

    private void setConcert(Concert concert) {
        this.concert = concert;
        concert.addSeats(this);
    }
}
