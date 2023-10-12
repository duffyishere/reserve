package org.duffy.reserve.domain.account;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.duffy.reserve.domain.account.dto.CreateSellerRequest;
import org.duffy.reserve.domain.concert.Concert;

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

    public SellerAccount(CreateSellerRequest request, String encryptedPassword) {
        super(request, encryptedPassword);
    }
}
