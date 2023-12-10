package org.duffy.ticketing.domain.concert.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.duffy.ticketing.domain.concert.Concert;
import org.duffy.ticketing.domain.concert.Seat;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.duffy.ticketing.domain.concert.QSeat.seat;

@Repository
@RequiredArgsConstructor
public class CustomSeatRepository {
    private final JPAQueryFactory queryFactory;

    /**
     * Finds seats based on seat numbers and a concert.
     *
     * @param numbers  A list of seat numbers
     * @param concert  The concert to search for seats in
     * @return A list of seats that match the given seat numbers and concert
     */
    public List<Seat> findAllBySeatNumbers(List<Integer> numbers, Concert concert) {
        return queryFactory.selectFrom(seat)
                .where(
                        seat.concert.eq(concert),
                        seat.seatNumber.in(numbers),
                        seat.isAddedToWishlist.isFalse()
                )
                .fetch();
    }
}
