package org.duffy.reserve.domain.concert.service;

import lombok.RequiredArgsConstructor;
import org.duffy.reserve.domain.concert.repository.ConcertRepository;
import org.duffy.reserve.domain.concert.repository.SeatRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ConcertService {
    private final ConcertRepository concertRepository;
    private final SeatRepository seatRepository;
}
