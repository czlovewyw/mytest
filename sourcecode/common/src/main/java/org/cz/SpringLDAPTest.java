package org.cz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.DefaultIncrementalAttributesMapper;
import org.springframework.ldap.query.LdapQueryBuilder;

import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.List;

/**
 * 使用spring-ldap Api进行ldap的相关操作
 * Created by chenzhe8 on 2017/3/28.
 */
public class SpringLDAPTest implements LDAPService {
    private LdapTemplate ldapTemplate = null;

    public SpringLDAPTest(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }


    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringLDAPTest myLdap = new SpringLDAPTest(ctx.getBean(LdapTemplate.class));
        long timeStart = System.currentTimeMillis();
        PersonObject person = LDAPDataBuiler.buildPerson();
        myLdap.addPerson(person);
//        System.out.println(myLdap.getAllUsers("chenzhe8"));
//        myLdap.deletePerson("cn=8c4167cff84743c98e13868fc9ad0177,o=publicSecurity");
//        myLdap.renamePerson("cn=065af6bb6e7f41bb87e8ddb1270350fc,o=publicSecurity","cn=065af6bb6e7f41bb87e8ddb1270350fc,o=publicTraffic");
//        myLdap.modifyPerson("cn=jinshi,o=publicSecurity","jinshi");
//        System.out.println(myLdap.lookup("cn=chenzhe9,o=publicSecurity"));
        System.out.println("用时：" + (System.currentTimeMillis() - timeStart) / 1000.0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public String getUserDN(String str) {
//        List<String> results = ldapTemplate.search(
//                LdapQueryBuilder.query().where("cn").is("jinshi"),
//                new AttributesMapper<String>() {
//                    public String mapFromAttributes(Attributes attrs)
//                            throws NamingException {
//                        return (String) attrs.get("cn").get();
//                    }
//                });
        List<String> results = ldapTemplate.search("","sn="+str, new AttributesMapper() {
            @Override
            public Object mapFromAttributes(Attributes attributes) throws NamingException {
                return attributes.get("description");
            }
        });
        return results.toString();
    }

    @Override
    public boolean authenricate(String ID, String password) {
        return false;
    }

    @Override
    public boolean addPerson(PersonObject person) {
        BasicAttributes attrsbu = new BasicAttributes();
        BasicAttribute objclassSet = new BasicAttribute("objectclass");
        objclassSet.add(person.OBJECT_CLASS);
        attrsbu.put(objclassSet);
        attrsbu.put("sn", person.getSn());
        attrsbu.put("cn", person.getCn());
        attrsbu.put("description", person.getDescription());
        attrsbu.put("telephoneNumber", person.getTelephoneNumber());
        attrsbu.put("userPassword", person.getUserPassword());
        this.ldapTemplate.bind("cn=" + person.getCn() + ",o=publicSecurity", null, attrsbu);
        return true;
    }

    @Override
    public boolean modifyPerson(String dn, String newValue) {
        Attribute attr = new BasicAttribute("description",newValue);
        ModificationItem item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attr);
        this.ldapTemplate.modifyAttributes(dn, new ModificationItem[]{item});
        return true;
    }

    @Override
    public boolean renamePerson(String oldDN, String newDN) {
        this.ldapTemplate.rename(oldDN,newDN);
        return true;
    }

    @Override
    public boolean deletePerson(String dn) {
        this.ldapTemplate.unbind(dn);
        return true;
    }

    /**
     * 查找所有用户
     * @param str
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<PersonObject> getAllUsers(String str) {
        List<PersonObject> results = ldapTemplate.search("","sn="+str, (AttributesMapper) attributes -> {
            PersonObject person = new PersonObject();
            Object cnObject = attributes.get("sn").get();
            person.setCn(cnObject instanceof byte[]?new String((byte[]) cnObject):(String) cnObject);
            Object snObject = attributes.get("cn").get();
            person.setSn(snObject instanceof byte[]?new String((byte[])snObject):(String)snObject);
            Object descriptionObject = attributes.get("description").get();
            person.setDescription(descriptionObject instanceof byte[]?new String((byte[])descriptionObject):(String)descriptionObject);
            return person;
        });
        return results;
    }

    @SuppressWarnings("unchecked")
    @Override
    public PersonObject lookup(String dn) {
        return (PersonObject) this.ldapTemplate.lookup(dn, (AttributesMapper) attributes -> {
            PersonObject user = new PersonObject();
            if(attributes!=null){
                PersonObject person = new PersonObject();
                Object cnObject = attributes.get("sn").get();
                person.setCn(cnObject instanceof byte[]?new String((byte[]) cnObject):(String) cnObject);
                Object snObject = attributes.get("cn").get();
                person.setSn(snObject instanceof byte[]?new String((byte[])snObject):(String)snObject);
                Object descriptionObject = attributes.get("description").get();
                person.setDescription(descriptionObject instanceof byte[]?new String((byte[])descriptionObject):(String)descriptionObject);
                return person;
            }
            return user;
        });
    }
}
