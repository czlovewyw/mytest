package org.leochen.samples.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * User: leochen
 * Date: 11-12-1
 * Time: 下午2:20
 */
@Entity
@Table(name = "hotel")
public class Hotel implements Serializable{
    private Long id;
    private String name;
    private String address;
    private String country;
    private String city;
    private String state;
    private String zip;
    private BigDecimal price;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Column(precision = 6, scale = 2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hotel(").append(name).append(", ")
                .append(address).append(", ")
                .append(city).append(", ")
                .append(zip).append(")");
        return sb.toString();
    }
}
