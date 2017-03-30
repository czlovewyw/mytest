package org.leochen.samples.service.impl;

import org.leochen.samples.dao.BookingRepository;
import org.leochen.samples.dao.HotelRepository;
import org.leochen.samples.dao.UserRepository;
import org.leochen.samples.domain.User;
import org.leochen.samples.web.controllers.hotels.SearchCriteria;
import org.leochen.samples.domain.Booking;
import org.leochen.samples.domain.Hotel;
import org.leochen.samples.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * User: leochen
 * Date: 11-12-1
 * Time: 下午1:41
 */

@Service("bookingService")
public class BookingServiceImpl implements BookingService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;



    public Page<Booking> findBookings(SearchCriteria searchCriteria, String username) {
        if(username!=null){
            int page = searchCriteria.getPage();
            int size = searchCriteria.getPageSize();
            Page<Booking> bookingPage = bookingRepository.findBookings(username,new PageRequest(page,size));
            return bookingPage;
        }
        return null;
    }

    public Page<Hotel> findHotels(SearchCriteria criteria) {
        int page = criteria.getPage();
        int size = criteria.getPageSize();
        System.out.println("page="+page);
        System.out.println("size="+size);
        Pageable pageable = new PageRequest(page, size);
        return hotelRepository.findHotels(getSearchPattern(criteria), pageable);
    }

    public Hotel findHotelById(Long id) {
        return hotelRepository.findOne(id);
    }

    public long getTotalElementsOfHotels() {
        return hotelRepository.count();
    }


    public Booking createBooking(Long hotelId, String username) {
        User user = userRepository.findUserByUsername(username);
        Hotel hotel = hotelRepository.findOne(hotelId);
        Booking booking = new Booking(user,hotel);
        return booking;
    }

    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        bookingRepository.delete(id);
    }

    private String getSearchPattern(SearchCriteria criteria) {
        if (StringUtils.hasText(criteria.getSearchString())) {
            return "%" + criteria.getSearchString().toLowerCase().replace('*', '%') + "%";
        } else {
            return "%";
        }
    }

}
