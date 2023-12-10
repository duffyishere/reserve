package org.duffy.ticketing.domain.concert.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.duffy.ticketing.domain.account.BuyerAccount;
import org.duffy.ticketing.domain.account.SellerAccount;
import org.duffy.ticketing.domain.concert.Concert;
import org.duffy.ticketing.domain.concert.ConcertReservationStatus;
import org.duffy.ticketing.domain.concert.Seat;
import org.duffy.ticketing.domain.concert.dto.CreateConcertRequest;
import org.duffy.ticketing.domain.concert.dto.GetConcertDetailResponse;
import org.duffy.ticketing.domain.concert.dto.ReserveConcertRequest;
import org.duffy.ticketing.domain.concert.dto.SeatResponse;
import org.duffy.ticketing.domain.concert.repository.ConcertRepository;
import org.duffy.ticketing.domain.concert.repository.ConcertReservationStatusRepository;
import org.duffy.ticketing.domain.concert.repository.CustomSeatRepository;
import org.duffy.ticketing.domain.concert.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Service
public class ConcertService {
    private final ConcertRepository concertRepository;
    private final SeatRepository seatRepository;
    private final CustomSeatRepository customSeatRepository;
    private final ConcertReservationStatusRepository reservationStatusRepository;

    @Transactional
    public void createConcert(SellerAccount seller, CreateConcertRequest body) {
        Concert concert = new Concert(seller, body);
        concertRepository.save(concert);

        List<Seat> seats = createSeats(concert, body.getSeatingCapacity());
        seatRepository.saveAll(seats);
    }

    private List<Seat> createSeats(Concert concert, int capacity) {
        return IntStream.rangeClosed(1, capacity)
                .mapToObj(seatNumber -> new Seat(seatNumber, concert))
                .collect(Collectors.toList());
    }

    public GetConcertDetailResponse getConcertDetail(Long concertId) {
        Concert concert = getConcertById(concertId);
        return new GetConcertDetailResponse(concert);
    }

    public List<SeatResponse> getSeatsFor(Long concertId) {
        Concert concert = getConcertById(concertId);
        return seatRepository.findByConcert(concert).stream()
                .map(SeatResponse::new)
                .toList();
    }

    @Transactional
    public void reserveConcert(BuyerAccount buyer, ReserveConcertRequest body) {
        Concert concert = getConcertById(body.getConcertId());
        if (!concert.isOpenTimePassed()) {
            throw new IllegalArgumentException("It's not time to open the reservation yet.");
        }
        List<Seat> selectedSeats = selectSeats(concert, body.getSeatNumbers());
        createConcertReservationStatus(buyer, concert, selectedSeats);
    }

    private List<Seat> selectSeats(Concert concert, List<Integer> seatNumbers) {
        List<Seat> seats = customSeatRepository.findAllBySeatNumbers(seatNumbers, concert);
        if (seats.size() != seatNumbers.size())
            throw new IllegalArgumentException("The number of requested seats is not available");

        concert.decreaseRemainSeatCount(seats.size());
        return seats;
    }

    private ConcertReservationStatus createConcertReservationStatus(BuyerAccount buyer, Concert concert, List<Seat> selectedSeats) {
        ConcertReservationStatus reservation = new ConcertReservationStatus(buyer, concert, selectedSeats);
        return reservationStatusRepository.save(reservation);
    }

    private Concert getConcertById(Long concertId) {
        return concertRepository.findById(concertId).orElseThrow(() -> new IllegalArgumentException("No such concert exists."));
    }
}
