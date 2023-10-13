package org.duffy.reserve.domain.concert;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.duffy.reserve.domain.account.DefaultAccount;
import org.duffy.reserve.domain.base.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class ConcertReservationStatus extends BaseTimeEntity {

    @Id
    private Long id;

    @ManyToOne
    private DefaultAccount buyer;

    @ManyToOne
    private Concert concert;

    @OneToMany
    private List<Seat> seats = new ArrayList<>();

    public ConcertReservationStatus(DefaultAccount buyer, Concert concert, List<Seat> seats) {

    }
    private void setBuyer(DefaultAccount buyer) {
        this.buyer = buyer;
        buyer.addReservation(this);
    }
}
