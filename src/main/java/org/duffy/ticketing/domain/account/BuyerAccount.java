package org.duffy.ticketing.domain.account;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.duffy.ticketing.domain.auth.dto.RegisterBuyerAccountRequest;
import org.duffy.ticketing.domain.wishlist.SeatWishlist;

import java.util.ArrayList;
import java.util.List;

@DiscriminatorValue("buyer")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Entity
@NoArgsConstructor
public class BuyerAccount extends Account {

    @OneToMany(mappedBy = "buyer")
    private List<SeatWishlist> seatWishList = new ArrayList<>();

    public BuyerAccount(RegisterBuyerAccountRequest request, String encryptedPassword) {
        super(request, encryptedPassword);
        setRole(Role.USER);
    }

    public void addToWishList(SeatWishlist seat) {
        seatWishList.add(seat);
    }
}
