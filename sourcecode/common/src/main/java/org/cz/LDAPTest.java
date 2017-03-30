package org.cz;

import com.sun.jndi.ldap.LdapCtx;

import javax.naming.*;
import javax.naming.directory.*;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.List;

/**
 * LDAP认证，采用的是两部验证法
 * 第一步：根据用户id找到全限定名；
 * 第二步：根据全限定名和密码进行身份验证；
 * Created by chenzhe8 on 2017/3/23.
 */
public class LDAPTest implements LDAPService {

    public LDAPTest() {
    }


    public static final String URL = "ldap://127.0.0.1:389/";

    /**
     * 基准DN，所有条目的查询、修改、增加操作DN都是基于此，会因基准DN的变动产生不同的结果
     */
    public static final String BASE_DN = "dc=mycompany,dc=com";
    public static final String FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";
    public static final String PRINCIPAL = "cn=manager,dc=mycompany,dc=com";
    public static final String CREDENTIALS = "secret";

    private LdapContext ctx = null;
    private Control[] connCtls = null;

    /**
     * 初始化LDAP连接
     */
    private void connectLDAP() {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, FACTORY);
        env.put(Context.PROVIDER_URL, URL + BASE_DN);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, PRINCIPAL);
        env.put(Context.SECURITY_CREDENTIALS, CREDENTIALS);
        try {
            ctx = new InitialLdapContext(env, connCtls);
            System.out.println("登录成功");
        } catch (javax.naming.AuthenticationException e) {
            e.printStackTrace();
            System.out.println("登录失败");
        } catch (Exception e) {
            System.out.println("登录出错：");
            e.printStackTrace();
        }

    }

    /**
     * 关闭LDAP连接
     */
    private void closeLDAP() {
        if (ctx != null) {
            try {
                ctx.close();
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 在指定目录树下查找符合条件的条目
     * 过滤器格式：
     *  (&(key1=value1)(key2=value2)) means (key1=value1) and (key2=value2)
     *  (|(key1=value1)(key2=value2)) means (key1=value1) or (key2=value2)
     * @param str
     * @return
     */
    @Override
    public String getUserDN(String str) {
        String userDN = "";

        try {
            SearchControls constraints = new SearchControls();
            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);

            //返回指定属性，不指定默认为null即返回全部属性，设置空数组则不返回任何属性
//            String[] returnedAtts = { "userPassword","description" };
//            constraints.setReturningAttributes(returnedAtts);

            NamingEnumeration en = ctx.search("o=publicSecurity", "(|(cn=" + str+")(cn=chenzhe9))", constraints);
            if (en == null) {
                System.out.println("Have no NamingEnumeration.");
            }
            if (!en.hasMoreElements()) {
                System.out.println("Have no element.");
            }
            while (en != null && en.hasMoreElements()) {
                Object obj = en.nextElement();
                userDN = "";
                if (obj instanceof SearchResult) {
                    SearchResult si = (SearchResult) obj;
                    //获取相对BASE_DN的一个相对名称，同一条目会因为登录的BASE_DN而得到不同的值
//                    userDN += si.getName();
                    System.out.println("rdn="+si.getName());
//                    userDN += "," + BASE_DN;
                    userDN = si.getNameInNamespace();
                    System.out.println(userDN + "\n");

                    //获取条目所有属性值
                    Attributes attrs = si.getAttributes();
                    if (attrs != null) {
                        NamingEnumeration myattrs = attrs.getAll();
                        while (myattrs != null && myattrs.hasMore()) {
//                            Object attrObj = myattrs.next();
//                            System.out.println(attrObj);
                            Attribute attr = (Attribute) myattrs.next();
                            String key = attr.getID();
                            StringBuffer valueBuffer = new StringBuffer();
                            NamingEnumeration values = attr.getAll();
                            boolean start = true;
                            while (values != null && values.hasMore()) {
                                if (!start) {
                                    valueBuffer.append(",");
                                }
                                Object valueObject = values.next();
                                if (valueObject instanceof byte[]) {
                                    valueBuffer.append(new String((byte[]) valueObject, "utf-8"));
                                } else {
                                    valueBuffer.append(valueObject.toString());
                                }
                                start = false;
                            }
                            System.out.println(key + "=" + valueBuffer.toString());
                        }
                    }
                } else {
                    System.out.println(obj);
                }
                System.out.println("====================");
            }
        } catch (Exception e) {
            System.out.println("Exception in search():" + e);
            e.printStackTrace();
        }

        return userDN;
    }

    /**
     * 验证用户身份
     *
     * @param ID       完整限定名
     * @param password 密码
     * @return
     */
    @Override
    public boolean authenricate(String ID, String password) {
        boolean valide = false;
        String userDN = getUserDN(ID);
        try {
            ctx.addToEnvironment(Context.SECURITY_PRINCIPAL, userDN);
            ctx.addToEnvironment(Context.SECURITY_CREDENTIALS, password);
            ctx.reconnect(connCtls);
            System.out.println(userDN + " is authenticated(验证成功！)");
            valide = true;
        } catch (AuthenticationException e) {
            System.out.println(userDN + " is not authenticated");
            System.out.println(e.toString());
            valide = false;
        } catch (NamingException e) {
            System.out.println(userDN + " is not authenticated");
            valide = false;
        }

        return valide;
    }

    /**
     * 增加人员条目
     *
     * @param person
     * @return
     */
    @Override
    public boolean addPerson(PersonObject person) {
        try {
            ctx.bind("cn=58859e949cd84fe2a4440b34058eb998",person,null);
        } catch (NamingException e) {
            e.printStackTrace();
        }

//        try {
//            BasicAttributes attrsbu = new BasicAttributes();
//            BasicAttribute objclassSet = new BasicAttribute("objectclass");
//            objclassSet.add(person.OBJECT_CLASS);
//            attrsbu.put(objclassSet);
//            attrsbu.put("sn", person.getSn());
//            attrsbu.put("cn", person.getCn());
//            attrsbu.put("description", person.getDescription());
//            attrsbu.put("telephoneNumber", person.getTelephoneNumber());
//            attrsbu.put("userPassword", person.getUserPassword());
//            //此处name参数值为相对base_dn的dn
////            ctx.createSubcontext("cn=" + person.getCn() + ",o=publicSecurity", attrsbu);
//            ctx.bind("cn=" + person.getCn() + ",o=publicSecurity",null,attrsbu);
//
//            return true;
//        } catch (NamingException ex) {
//            ex.printStackTrace();
//        }
        return false;
    }

    /**
     * 修改用户条目
     * @param dn 相对base_dn的dn
     * @param newValue
     * @return
     */
    @Override
    public boolean modifyPerson(String dn, String newValue) {
        try {
            ModificationItem[] mods = new ModificationItem[1];

            /* 添加属性，属性必须在schema中定义过，否则失败 */
            Attribute attr0 = new BasicAttribute("description", newValue);
            mods[0] = new ModificationItem(DirContext.ADD_ATTRIBUTE, attr0);
            /* 修改属性*/
//            Attribute attr0 = new BasicAttribute("description", newValue);
//            mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attr0);
            /* 删除属性*/
//            Attribute attr0 = new BasicAttribute("description", newValue);
//            mods[0] = new ModificationItem(DirContext.REMOVE_ATTRIBUTE, attr0);

            ctx.modifyAttributes(dn, mods);
            return true;
        } catch (NamingException e) {
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

    /**
     * 重命名用户条目，通过此操作可以实现条目在树路径上的上下移动
     *
     * @param oldDN 相对base_dn的dn
     * @param newDN 相对base_dn的dn
     * @return
     */
    @Override
    public boolean renamePerson(String oldDN, String newDN) {
        try {
            ctx.rename(oldDN, newDN);
            return true;
        } catch (NamingException ne) {
            System.err.println("Error: " + ne.getMessage());
            return false;
        }
    }

    /**
     * 删除人员条目
     *
     * 如果条目下有子条目，则无法删除
     * @param dn 相对base_dn的dn
     * @return
     */
    @Override
    public boolean deletePerson(String dn) {
        try {
//            ctx.destroySubcontext(dn);
            ctx.unbind(dn);
            return true;
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 查找所有人员条目
     * @param str
     * @return
     */
    @Override
    public List<PersonObject> getAllUsers(String str) {
        return null;
    }

    @Override
    public PersonObject lookup(String dn) {
//        try {
//            return (LdapCtx) ctx.lookup(dn);
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
        try {
            Attributes answer = ctx.getAttributes(dn);
            NamingEnumeration myattrs = answer.getAll();
            while (myattrs != null && myattrs.hasMore()) {
                Attribute attr = (Attribute) myattrs.next();
                String key = attr.getID();
                StringBuffer valueBuffer = new StringBuffer();
                NamingEnumeration values = attr.getAll();
                boolean start = true;
                while (values != null && values.hasMore()) {
                    if (!start) {
                        valueBuffer.append(",");
                    }
                    Object valueObject = values.next();
                    if (valueObject instanceof byte[]) {
                        valueBuffer.append(new String((byte[]) valueObject, "utf-8"));
                    } else {
                        valueBuffer.append(valueObject.toString());
                    }
                    start = false;
                }
                System.out.println(key + "=" + valueBuffer.toString());
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void listBinds(String dn){
        NamingEnumeration list = null;
        try {
            list = ctx.listBindings(dn);
            while (list.hasMore()) {
                Binding nc = (Binding)list.next();
                System.out.println(nc.getName()+":"+nc.getObject());
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }


    }

    public void list(String dn){
        NamingEnumeration list = null;
        try {
            list = ctx.list(dn);
            while (list.hasMore()) {
                NameClassPair nc = (NameClassPair)list.next();
                System.out.println(nc);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        LDAPTest ldapTest = new LDAPTest();
        ldapTest.connectLDAP();
        long timeStart = System.currentTimeMillis();
//        String dn = ldapTest.getUserDN("jinshi");
//        ldapTest.authenricate("chenzhe8","123456");
//        ldapTest.authenricate("jinshi","123456");
//        PersonObject person = LDAPDataBuiler.buildPerson();
//        ldapTest.addPerson(person);
//        ldapTest.deletePerson("cn=60abc6d1600b4377948135c4ca4e4e24,o=publicSecurity");
//        ldapTest.renamePerson("cn=chenzhe9,o=publicSecurity","cn=chenzhe9,o=publicSecurity2");
//        ldapTest.modifyPerson("cn=chenzhe9,o=publicSecurity","aaa");
//        System.out.println(ldapTest.lookup("cn=chenzhe9,o=publicSecurity"));
        ldapTest.listBinds("o=publicSecurity");
        System.out.println("用时：" + (System.currentTimeMillis() - timeStart) / 1000.0);
        ldapTest.closeLDAP();
    }
}