package org.cz;

import java.util.UUID;

/**
 * Created by chenzhe8 on 2017/3/28.
 */
public class LDAPDataBuiler {
    public static void main(String[] args) {

    }

    public static PersonObject buildPerson() {
        PersonObject person = new PersonObject();
        person.setCn(UUID.randomUUID().toString().replaceAll("-", ""));
        person.setSn("short");
        person.setTelephoneNumber("111");
        person.setDescription("这是一个测试用户");
        person.setUserPassword("123456");
        return person;
    }

    public void buildPeopleDatas(){

    }
}
