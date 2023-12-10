package org.duffy.ticketing.domain.concert;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private boolean isAddedToWishlist;

    private boolean isPaid;

    public Seat(int seatNumber, Concert concert) {
        this.seatNumber = seatNumber;
        this.isAddedToWishlist = false;
        this.isPaid = false;
        setConcert(concert);
    }

    private void setConcert(Concert concert) {
        this.concert = concert;
        concert.addSeats(this);
    }

    public void select() {
        this.isAddedToWishlist = true;
    }
}
