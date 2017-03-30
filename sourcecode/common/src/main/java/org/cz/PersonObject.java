package org.cz;

import java.io.Serializable;

/**
 * Created by chenzhe8 on 2017/3/28.
 */
public class PersonObject implements Serializable{
    public static final String OBJECT_CLASS = "person";
    private static final long serialVersionUID = 2770716917596433060L;
    /**
     * 描述
     */
    private String description;
    /**
     * 姓
     */
    private String sn;
    /**
     * 全名
     */
    private String cn;
    /**
     * 电话
     */
    private String telephoneNumber;
    /**
     * 密码
     */
    private String userPassword;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "PersonObject{" +
                "description='" + description + '\'' +
                ", sn='" + sn + '\'' +
                ", cn='" + cn + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
