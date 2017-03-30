package org.cz;

import java.util.List;

/**
 * Created by chenzhe8 on 2017/3/28.
 */
public interface LDAPService {
    String getUserDN(String str);

    boolean authenricate(String ID, String password);

    boolean addPerson(PersonObject person);

    boolean modifyPerson(String dn, String newValue);

    boolean renamePerson(String oldDN, String newDN);

    boolean deletePerson(String dn);

    List<PersonObject> getAllUsers(String str);

    PersonObject lookup(String dn);
}
