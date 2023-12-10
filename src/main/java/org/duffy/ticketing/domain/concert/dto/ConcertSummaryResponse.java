package org.duffy.ticketing.domain.concert.dto;

import lombok.Getter;
import org.duffy.ticketing.domain.concert.Concert;

@Getter
public class ConcertSummaryResponse {
    private long id;
    private String title;
    private String thumbnailURL;

    public ConcertSummaryResponse(Concert concert) {
        this.id = concert.getId();
        this.title = concert.getTitle();
        this.thumbnailURL = concert.getThumbnailURL();
    }
}
