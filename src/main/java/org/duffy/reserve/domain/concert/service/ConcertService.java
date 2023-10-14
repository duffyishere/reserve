package org.duffy.reserve.domain.concert.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.duffy.reserve.domain.account.BuyerAccount;
import org.duffy.reserve.domain.account.SellerAccount;
import org.duffy.reserve.domain.concert.Concert;
import org.duffy.reserve.domain.concert.ConcertReservationStatus;
import org.duffy.reserve.domain.concert.Seat;
import org.duffy.reserve.domain.concert.dto.CreateConcertRequest;
import org.duffy.reserve.domain.concert.dto.GetConcertDetailResponse;
import org.duffy.reserve.domain.concert.dto.ReserveConcertRequest;
import org.duffy.reserve.domain.concert.dto.SeatResponse;
import org.duffy.reserve.domain.concert.repository.ConcertRepository;
import org.duffy.reserve.domain.concert.repository.ConcertReservationStatusRepository;
import org.duffy.reserve.domain.concert.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ConcertService {
    private final ConcertRepository concertRepository;
    private final SeatRepository seatRepository;
    private final ConcertReservationStatusRepository reservationStatusRepository;

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
    // 콘서트 상세 조회
    public GetConcertDetailResponse getConcertDetail(Long concertId) {
        Concert concert = getConcertById(concertId);
        return concert.toResponse();
    }

    // 콘서트 좌석 조회
    public List<SeatResponse> getSeatsFor(Long concertId) {
        Concert concert = getConcertById(concertId);
        return seatRepository.findByConcert(concert).stream()
                .map(SeatResponse::new)
                .toList();
    }

    @Transactional
    // 콘서트 예약하기
    public void reserveConcert(BuyerAccount buyer, ReserveConcertRequest body) {
        Concert concert = getConcertById(body.getConcertId());
        List<Seat> selectedSeats = selectSeats(concert, body.getSeatNumbers());
        createConcertReservationStatus(buyer, concert, selectedSeats);
    }

    private List<Seat> selectSeats(Concert concert, List<Integer> seatNumbers) {
        List<Seat> seats = new ArrayList<>();
        for (int seatNumber: seatNumbers) {
            Seat seat = seatRepository.findByConcertAndSeatNumber(concert, seatNumber).orElseThrow(() -> new IllegalArgumentException("No such seats exists."));
            if (!isSelectable(seat)) throw new IllegalArgumentException("That seat is already reserved.");
            seat.select();
            seats.add(seat);
        }
        return seats;
    }

    private ConcertReservationStatus createConcertReservationStatus(BuyerAccount buyer, Concert concert, List<Seat> selectedSeats) {
        ConcertReservationStatus reservation = new ConcertReservationStatus(buyer, concert, selectedSeats);
        return reservationStatusRepository.save(reservation);
    }

    private boolean isSelectable(Seat seat) {
        return !seat.isReservation();
    }

    private Concert getConcertById(Long concertId) {
        return concertRepository.findById(concertId).orElseThrow(() -> new IllegalArgumentException("No such concert exists."));
    }
}
