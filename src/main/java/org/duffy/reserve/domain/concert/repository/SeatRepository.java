package org.duffy.reserve.domain.concert.repository;

import org.duffy.reserve.domain.concert.Concert;
import org.duffy.reserve.domain.concert.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByConcert(Concert concert);
}
