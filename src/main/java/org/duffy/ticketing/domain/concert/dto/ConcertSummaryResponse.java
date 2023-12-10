package org.duffy.ticketing.domain.concert.dto;

import org.duffy.ticketing.domain.concert.Concert;

public record ConcertSummaryResponse(long id, String title, String thumbnailURL) {
    public ConcertSummaryResponse(Concert concert) {
        this(concert.getId(), concert.getTitle(), concert.getThumbnailURL());
    }
}