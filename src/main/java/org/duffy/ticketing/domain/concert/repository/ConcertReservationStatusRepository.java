package org.duffy.ticketing.domain.concert.repository;

import org.duffy.ticketing.domain.concert.ConcertReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcertReservationStatusRepository extends JpaRepository<ConcertReservationStatus, Long> {
}
