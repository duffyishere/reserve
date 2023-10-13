package org.duffy.reserve.domain.concert.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReserveConcertRequest {
    private Long concertId;
    private List<Integer> seatNumbers;
}
