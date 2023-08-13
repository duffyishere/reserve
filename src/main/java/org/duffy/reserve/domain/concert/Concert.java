package org.duffy.reserve.domain.concert;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Concert {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "concert")
    private List<Seat> seats = new ArrayList<>();
}
