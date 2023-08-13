package org.duffy.reserve.domain.concert.repository;

import org.duffy.reserve.domain.concert.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertRepository extends JpaRepository<Concert, Long> {
}
