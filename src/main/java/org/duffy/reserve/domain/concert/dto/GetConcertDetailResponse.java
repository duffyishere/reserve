package org.duffy.reserve.domain.concert.dto;

import lombok.Data;
import org.duffy.reserve.domain.concert.Concert;

@Data
public class GetConcertDetailResponse {
    private Long id;
    private String title;
    private String description;
    private String thumbnailURL;

    private String openDateTime;
    private String closeDateTime;

    private int seatingCapacity = 0;
    private int remainingSeats = 0;

    public GetConcertDetailResponse(Concert concert) {

    }
}
