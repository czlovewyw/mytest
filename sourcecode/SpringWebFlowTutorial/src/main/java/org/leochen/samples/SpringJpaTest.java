package org.leochen.samples;

import org.leochen.samples.dao.HotelRepository;
import org.leochen.samples.domain.Hotel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * User: leochen
 * Date: 11-12-11
 * Time: 下午4:20
 */
public class SpringJpaTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext-persistence.xml");

        HotelRepository hotelRepository = context.getBean(HotelRepository.class);

        System.out.println("hotelRepository=" + hotelRepository);

        Page<Hotel> pageHotel=hotelRepository.findHotels("%",new PageRequest(2,5));

        List<Hotel> list= pageHotel.getContent();
        System.out.println("totalElements="+pageHotel.getTotalElements());
        System.out.println("totalPages="+pageHotel.getTotalPages());
        System.out.println("getNumber="+pageHotel.getNumber());
        System.out.println("size="+pageHotel.getSize());

        for(Hotel  h : list){
            System.out.println(h.getId());
        }

    }
}
