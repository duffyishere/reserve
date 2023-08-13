package org.duffy.reserve.domain.concert;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class Seat {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Concert concert;
}
