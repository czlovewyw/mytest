package org.leochen.samples.service;

import org.leochen.samples.web.controllers.hotels.SearchCriteria;
import org.leochen.samples.domain.Booking;
import org.leochen.samples.domain.Hotel;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * User: leochen
 * Date: 11-12-1
 * Time: 下午1:35
 */
public interface BookingService {

    Page<Booking> findBookings(SearchCriteria searchCriteria, String username);

    Page<Hotel> findHotels(SearchCriteria criteria);

    Hotel findHotelById(Long id);

    long getTotalElementsOfHotels();

    Booking createBooking(Long hotelId, String username);

    Booking save(Booking booking);

    void deleteBooking(Long id);

}
