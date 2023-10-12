package org.duffy.reserve.domain.account;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import org.duffy.reserve.domain.concert.Concert;

import java.util.ArrayList;
import java.util.List;

@DiscriminatorValue("seller")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Entity
public class SellerAccount extends Account {

    @OneToMany(mappedBy = "sellerAccount")
    private List<Concert> createdConcerts = new ArrayList<>();
}
