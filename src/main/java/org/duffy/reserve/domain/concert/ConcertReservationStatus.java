package org.duffy.reserve.domain.concert;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.duffy.reserve.domain.account.BuyerAccount;
import org.duffy.reserve.domain.base.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class ConcertReservationStatus extends BaseTimeEntity {

    @Id
    private Long id;

    @ManyToOne
    private BuyerAccount buyer;

    @ManyToOne
    private Concert concert;

    @OneToMany
    private List<Seat> seats = new ArrayList<>();

    public ConcertReservationStatus(BuyerAccount buyer, Concert concert, List<Seat> seats) {

    }
    private void setBuyer(BuyerAccount buyer) {
        this.buyer = buyer;
        buyer.addReservation(this);
    }
}
