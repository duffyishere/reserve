package org.duffy.reserve.domain.account;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.PrimaryKeyJoinColumn;

@DiscriminatorValue("seller")
@PrimaryKeyJoinColumn(name = "id")
public class SellerAccount extends Account {
}
