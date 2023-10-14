package org.duffy.reserve.domain.concert.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReserveConcertRequest {
    private Long concertId;
    private List<Integer> seatNumbers;
}
