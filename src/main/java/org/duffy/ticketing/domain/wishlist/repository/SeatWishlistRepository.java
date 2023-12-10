package org.duffy.ticketing.domain.wishlist.repository;

import org.duffy.ticketing.domain.account.BuyerAccount;
import org.duffy.ticketing.domain.wishlist.SeatWishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatWishlistRepository extends JpaRepository<SeatWishlist, Long> {
    List<SeatWishlist> findByBuyer(BuyerAccount buyerAccount);
}
