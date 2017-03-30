package org.leochen.samples.domain;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.leochen.samples.web.validation.DateRange;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * User: leochen
 * Date: 11-12-1
 * Time: 下午1:37
 */
@Entity
@Table(name = "booking")
@DateRange(date = "checkinDate", futureDate = "checkoutDate")
public class Booking implements Serializable {
    private Long id;
    private Date checkinDate;
    private Date checkoutDate;
    private String creditCard;
    private String creditCardName;
    private int creditCardExpiryMonth;
    private int creditCardExpiryYear;
    private boolean smoking;
    private int beds;
    private Set<Amenity> amenities = new HashSet<Amenity>();

    private User user;
    private Hotel hotel;

    public Booking() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        setCheckinDate(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        setCheckoutDate(calendar.getTime());
    }

    public Booking(User user, Hotel hotel) {
        this();
        this.user = user;
        this.hotel = hotel;
    }


    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future
    @Temporal(TemporalType.DATE)
    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future
    @Temporal(TemporalType.DATE)
    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    @NotBlank
    @Pattern(regexp = "[0-9]{16}")
    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    @NotBlank
    public String getCreditCardName() {
        return creditCardName;
    }

    public void setCreditCardName(String creditCardName) {
        this.creditCardName = creditCardName;
    }

    @Range(min = 1, max = 12)
    public int getCreditCardExpiryMonth() {
        return creditCardExpiryMonth;
    }

    public void setCreditCardExpiryMonth(int creditCardExpiryMonth) {
        this.creditCardExpiryMonth = creditCardExpiryMonth;
    }

    public int getCreditCardExpiryYear() {
        return creditCardExpiryYear;
    }

    public void setCreditCardExpiryYear(int creditCardExpiryYear) {
        this.creditCardExpiryYear = creditCardExpiryYear;
    }

    public boolean isSmoking() {
        return smoking;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    @ElementCollection
    @CollectionTable(name = "booking_amenities", joinColumns = {@JoinColumn(name = "booking_id")})
    @Enumerated(EnumType.STRING)
    @Column(name = "amenity")
    public Set<Amenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(Set<Amenity> amenities) {
        this.amenities = amenities;
    }

    @ManyToOne
    @JoinColumn(name = "username")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }


    @Transient
    public int getNights() {
        if (checkinDate == null || checkoutDate == null) {
            return 0;
        } else {
            return (int) ((checkoutDate.getTime() - checkinDate.getTime()) / 1000 / 60 / 60 / 24);
        }
    }

    @Transient
    public BigDecimal getTotal() {
	    return hotel.getPrice().multiply(new BigDecimal(getNights()));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(user).append(": book ").append(hotel).append("]");
        return sb.toString();
    }
}
