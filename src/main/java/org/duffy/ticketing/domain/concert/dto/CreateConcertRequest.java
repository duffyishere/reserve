package org.duffy.ticketing.domain.concert.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateConcertRequest {
    private String title;
    private String description;
    private String thumbnailURL;

    private LocalDateTime openDateTime;
    private LocalDateTime closeDateTime;

    private int seatingCapacity;
}
