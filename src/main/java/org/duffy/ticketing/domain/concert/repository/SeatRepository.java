package org.duffy.ticketing.domain.concert.repository;

import org.duffy.ticketing.domain.concert.Concert;
import org.duffy.ticketing.domain.concert.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByConcert(Concert concert);
    Optional<Seat> findByConcertAndSeatNumber(Concert concert, int seatNumbers);
}
