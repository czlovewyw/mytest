package org.cz.h2.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

/**
 * Created by chenzhe8 on 2017/6/14.
 */
public class Test {
    public static void main(String[] args) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(User.class);

        Marshaller marshaller = context.createMarshaller();
        Unmarshaller unmarshaller = context.createUnmarshaller();

        User boy = new User();
        boy.setName("chenzhe");
        boy.setAddress("zhejiang");
        marshaller.marshal(boy, System.out);
        System.out.println();

//        String xml = "<boy><name>David</name></boy>";
//        User boy2 = (User) unmarshaller.unmarshal(new StringReader(xml));
//        System.out.println(boy2.getName());
    }
}
