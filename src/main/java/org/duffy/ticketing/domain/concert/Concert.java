package org.duffy.ticketing.domain.concert;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.duffy.ticketing.domain.account.SellerAccount;
import org.duffy.ticketing.domain.base.BaseTimeEntity;
import org.duffy.ticketing.domain.concert.dto.CreateConcertRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Concert extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @Lob
    private String description;
    @Lob
    private String thumbnailURL;

    private LocalDateTime openDateTime;
    private LocalDateTime closeDateTime;

    @ManyToOne
    private SellerAccount sellerAccount;

    @OneToMany(mappedBy = "concert")
    private List<Seat> seats = new ArrayList<>();
    private int seatingCapacity = 0;
    private int remainingSeats = 0;

    public Concert(SellerAccount seller, CreateConcertRequest request) {
        this.title = request.getTitle();
        this.description = request.getDescription();
        this.thumbnailURL = request.getThumbnailURL();
        this.openDateTime = request.getOpenDateTime();
        this.closeDateTime = request.getCloseDateTime();
        this.seatingCapacity = request.getSeatingCapacity();
        this.remainingSeats = request.getSeatingCapacity();
        setSellerAccount(seller);
    }

    private void setSellerAccount(SellerAccount sellerAccount) {
        this.sellerAccount = sellerAccount;
        sellerAccount.addConcert(this);
    }

    public void addSeats(Seat seat) {
        this.seats.add(seat);
    }
    public void decreaseRemainSeatCount(int num) {
        this.remainingSeats -= num;
    }

    public boolean isOpenTimePassed() {
        return LocalDateTime.now().isAfter(this.getOpenDateTime());
    }
}
