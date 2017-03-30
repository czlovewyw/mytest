package org.leochen.samples.web.controllers.hotels;

import org.leochen.samples.domain.Booking;
import org.leochen.samples.domain.Hotel;
import org.leochen.samples.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: leochen
 * Date: 11-12-1
 * Time: 下午1:06
 */

@Controller
public class HotelsController {

    @Autowired
    private BookingService bookingService;

    @RequestMapping(value = "/hotels/search",method = RequestMethod.GET)
    public void search(SearchCriteria searchCriteria,Principal currentUser,Map<String,Object> model){
        if(currentUser!=null){
            Page<Booking> bookingPage = bookingService.findBookings(searchCriteria,currentUser.getName());
            model.put("bookingPage",bookingPage);
        }
    }

    @RequestMapping(value = "/hotels", method = RequestMethod.GET)
    public String list(SearchCriteria searchCriteria, Map<String,Object> model) {
	    Page<Hotel> pageHotel = bookingService.findHotels(searchCriteria);
	    model.put("pageHotel",pageHotel);
	    return "hotels/list";
    }

    @RequestMapping(value = "/hotels/{id}", method = RequestMethod.GET)
    public String show(@PathVariable Long id,Map<String,Object> model){
        if(id <= 0){
            return "404";
        }

        long totalElements = bookingService.getTotalElementsOfHotels();
        if(id > totalElements){
            return "404";
        }

        Hotel hotel = bookingService.findHotelById(id);
        model.put("hotel",hotel);
        return "hotels/show";
    }

    @RequestMapping(value = "/bookings/{id}",method = RequestMethod.DELETE)
    public String deleteBooking(@PathVariable Long id){
        if(id <= 0){
            return "404";
        }

        bookingService.deleteBooking(id);
        return "redirect:../hotels/search";
    }
}
