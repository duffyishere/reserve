package org.duffy.reserve.domain.account;

import jakarta.persistence.*;
import lombok.Getter;
import org.duffy.reserve.domain.concert.ConcertReservationStatus;

import java.util.ArrayList;
import java.util.List;

@DiscriminatorValue("default")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Entity
public class DefaultAccount extends Account {

    @OneToMany(mappedBy = "buyer")
    private List<ConcertReservationStatus> reservedConcerts = new ArrayList<>();

    public void addReservation(ConcertReservationStatus reservation) {
        reservedConcerts.add(reservation);
    }
}
