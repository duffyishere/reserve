package org.duffy.ticketing.domain.account;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.duffy.ticketing.domain.account.dto.CreateBuyerAccountRequest;
import org.duffy.ticketing.domain.concert.ConcertReservationStatus;

import java.util.ArrayList;
import java.util.List;

@DiscriminatorValue("buyer")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Entity
@NoArgsConstructor
public class BuyerAccount extends Account {

    @OneToMany(mappedBy = "buyer")
    private List<ConcertReservationStatus> reservedConcerts = new ArrayList<>();

    public BuyerAccount(CreateBuyerAccountRequest request, String encryptedPassword) {
        super(request, encryptedPassword);
    }

    public void addReservation(ConcertReservationStatus reservation) {
        reservedConcerts.add(reservation);
    }
}
