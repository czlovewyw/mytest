package org.leochen.samples.dao;

import org.leochen.samples.domain.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * User: leochen
 * Date: 11-12-11
 * Time: 下午3:53
 */
public interface HotelRepository extends PagingAndSortingRepository<Hotel, Long> {

    @Query("select h from Hotel h where lower(h.name) like :pattern or lower(h.city) like :pattern or lower(h.zip) like :pattern or lower(h.address) like :pattern")
    Page<Hotel> findHotels(@Param("pattern") String pattern,Pageable pageable);

}
