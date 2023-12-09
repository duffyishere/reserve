package org.duffy.ticketing.domain.concert.dto;

import lombok.Data;
import org.duffy.ticketing.domain.concert.Concert;
import org.duffy.ticketing.global.Utils;

@Data
public class GetConcertDetailResponse {
    private Long id;
    private String title;
    private String description;
    private String thumbnailURL;

    private String openDateTime;
    private String closeDateTime;

    private int seatingCapacity;
    private int remainingSeats;

    public GetConcertDetailResponse(Concert concert) {
        this.id = concert.getId();
        this.title = concert.getTitle();
        this.description = concert.getDescription();
        this.thumbnailURL = concert.getThumbnailURL();
        this.openDateTime = Utils.dateTimeToString(concert.getOpenDateTime());
        this.closeDateTime = Utils.dateTimeToString(concert.getCloseDateTime());
        this.seatingCapacity = concert.getSeatingCapacity();
        this.remainingSeats = concert.getRemainingSeats();
    }
}
