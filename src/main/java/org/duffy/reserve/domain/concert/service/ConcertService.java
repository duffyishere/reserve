package org.duffy.reserve.domain.concert.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.duffy.reserve.domain.account.SellerAccount;
import org.duffy.reserve.domain.concert.Concert;
import org.duffy.reserve.domain.concert.dto.CreateConcertRequest;
import org.duffy.reserve.domain.concert.repository.ConcertRepository;
import org.duffy.reserve.domain.concert.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.beans.Transient;

@RequiredArgsConstructor
@Service
public class ConcertService {

    private final ConcertRepository concertRepository;

    @Transactional
    public void createConcert(SellerAccount seller, CreateConcertRequest body) {
        Concert concert = new Concert(seller, body);
        concertRepository.save(concert);
    }
}
