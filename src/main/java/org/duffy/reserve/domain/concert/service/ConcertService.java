package org.duffy.reserve.domain.concert.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.duffy.reserve.domain.account.SellerAccount;
import org.duffy.reserve.domain.concert.Concert;
import org.duffy.reserve.domain.concert.Seat;
import org.duffy.reserve.domain.concert.dto.CreateConcertRequest;
import org.duffy.reserve.domain.concert.repository.ConcertRepository;
import org.duffy.reserve.domain.concert.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ConcertService {
    private final ConcertRepository concertRepository;
    private final SeatRepository seatRepository;

    @Transactional
    public void createConcert(SellerAccount seller, CreateConcertRequest body) {
        Concert concert = new Concert(seller, body);
        concertRepository.save(concert);

        List<Seat> seats = createSeats(concert, body.getSeatingCapacity());
        seatRepository.saveAll(seats);
    }

    private List<Seat> createSeats(Concert concert, int capacity) {
        List<Seat> seats = new ArrayList<>();
        for (int seatNumber = 1; seatNumber <= capacity; seatNumber++) {
            seats.add(new Seat(seatNumber, concert));
        }
        return seats;
    }

    // 콘서트 수정
    // 콘서트 조회
    // 콘서트 남은 좌석 조회
    // 콘서트 예매
    // 콘서트 찜하기
}
