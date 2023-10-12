package org.duffy.reserve.domain.concert;

import jakarta.persistence.*;
import lombok.Getter;
import org.duffy.reserve.domain.account.SellerAccount;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Concert {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @Lob
    private String description;
    @Lob
    private String thumbnailURL;

    private LocalDateTime openDateTime;
    private LocalDateTime closeDateTime;

    @ManyToOne
    private SellerAccount sellerAccount;

    @OneToMany(mappedBy = "concert")
    private List<Seat> seats = new ArrayList<>();

    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    public Concert() {

    }
    public void addSeats(Seat seat) {
        seats.add(seat);
    }
}
