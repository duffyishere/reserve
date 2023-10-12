package org.duffy.reserve.domain.concert.repository;

import org.duffy.reserve.domain.concert.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, Long> {
}
