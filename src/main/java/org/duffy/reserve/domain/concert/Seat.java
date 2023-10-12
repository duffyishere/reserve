package org.duffy.reserve.domain.concert;

import jakarta.persistence.*;
import lombok.Getter;
import org.duffy.reserve.domain.account.DefaultAccount;

import java.time.LocalDateTime;

@Entity
@Getter
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

    private void setConcert(Concert concert) {
        this.concert = concert;
        concert.addSeats(this);
    }
}
