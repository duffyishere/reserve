package org.duffy.ticketing.domain.account;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.duffy.ticketing.domain.auth.dto.RegisterSellerAccountRequest;
import org.duffy.ticketing.domain.concert.Concert;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("seller")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@NoArgsConstructor
public class SellerAccount extends Account {

    @OneToMany(mappedBy = "sellerAccount")
    private List<Concert> createdConcerts = new ArrayList<>();

    public void addConcert(Concert concert) {
        this.createdConcerts.add(concert);
    }

    public SellerAccount(RegisterSellerAccountRequest request, String encryptedPassword) {
        super(request, encryptedPassword);
        setRole(Role.SELLER);
    }
}
