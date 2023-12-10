package org.duffy.ticketing.domain.concert.dto;

import org.duffy.ticketing.domain.concert.Concert;
import org.duffy.ticketing.global.Utils;

public record GetConcertDetailResponse(Long id, String title, String description, String thumbnailURL,
                                       String openDateTime, String closeDateTime,
                                       int seatingCapacity, int remainingSeats) {
    public GetConcertDetailResponse(Concert concert) {
        this(concert.getId(),
                concert.getTitle(),
                concert.getDescription(),
                concert.getThumbnailURL(),
                Utils.dateTimeToString(concert.getOpenDateTime()),
                Utils.dateTimeToString(concert.getCloseDateTime()),
                concert.getSeatingCapacity(),
                concert.getRemainingSeats());
    }
}
