package org.leochen.samples.dao;

import org.leochen.samples.domain.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * User: leochen
 * Date: 11-12-15
 * Time: 下午3:45
 */
public interface BookingRepository extends PagingAndSortingRepository<Booking,Long> {

    @Query("select b from Booking b where b.user.username=? order by b.checkinDate")
    Page<Booking> findBookings(String username,Pageable pageable);
}
