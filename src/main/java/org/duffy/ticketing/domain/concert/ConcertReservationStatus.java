package org.duffy.ticketing.domain.concert;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.duffy.ticketing.domain.account.BuyerAccount;
import org.duffy.ticketing.domain.base.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class ConcertReservationStatus extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private BuyerAccount buyer;

    @ManyToOne
    private Concert concert;

    @OneToMany
    private List<Seat> seats = new ArrayList<>();

    public ConcertReservationStatus(BuyerAccount buyer, Concert concert, List<Seat> seats) {
        setBuyer(buyer);
        this.concert = concert;
        this.seats = seats;
    }
    private void setBuyer(BuyerAccount buyer) {
        this.buyer = buyer;
        buyer.addReservation(this);
    }
}
