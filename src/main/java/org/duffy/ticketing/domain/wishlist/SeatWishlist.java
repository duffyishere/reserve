package org.duffy.ticketing.domain.wishlist;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.duffy.ticketing.domain.account.BuyerAccount;
import org.duffy.ticketing.domain.base.BaseTimeEntity;
import org.duffy.ticketing.domain.concert.Concert;
import org.duffy.ticketing.domain.concert.Seat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class SeatWishlist extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private BuyerAccount buyer;

    @ManyToOne
    private Concert concert;
    @OneToMany
    private List<Seat> seats = new ArrayList<>();

    private LocalDateTime paymentDeadline;

    public SeatWishlist(BuyerAccount buyer, Concert concert, List<Seat> seats, long paymentPeriodInDays) {
        setBuyer(buyer);
        this.concert = concert;
        this.seats = seats;
        this.paymentDeadline = LocalDateTime.now().plusDays(paymentPeriodInDays);
    }

    private void setBuyer(BuyerAccount buyer) {
        this.buyer = buyer;
        buyer.addToWishList(this);
    }
}
