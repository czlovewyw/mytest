package org.cz.h2.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by chenzhe8 on 2017/6/14.
 */
@XmlRootElement(name = "cz",namespace = "http://aaaaaa")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    private String name;
    private String address;

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
}
