package org.duffy.ticketing.domain.concert.dto;

import java.time.LocalDateTime;

public record CreateConcertRequest(String title, String description, String thumbnailURL,
                                   LocalDateTime openDateTime, LocalDateTime closeDateTime, int seatingCapacity) {
}