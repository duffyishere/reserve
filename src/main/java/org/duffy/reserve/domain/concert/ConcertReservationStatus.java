package org.duffy.reserve.domain.concert;

import jakarta.persistence.*;
import org.duffy.reserve.domain.account.DefaultAccount;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ConcertReservationStatus {

    @Id
    private Long id;

    @ManyToOne
    private DefaultAccount buyer;

    @ManyToOne
    private Concert concert;

    @OneToMany
    private List<Seat> seats = new ArrayList<>();

    private LocalDateTime createdDateTime;
}
