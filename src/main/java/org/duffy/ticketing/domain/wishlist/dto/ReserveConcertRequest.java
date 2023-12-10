package org.duffy.ticketing.domain.wishlist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public record ReserveConcertRequest(Long concertId, List<Integer> seatNumbers) {
}