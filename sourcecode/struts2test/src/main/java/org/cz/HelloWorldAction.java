package org.cz;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Inject;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chenzhe8 on 2017/3/9.
 */
public class HelloWorldAction extends ActionSupport
{
    public final static String MESSAGE = "Struts2 is up and running ...";

    private String message;

    @Inject("developer")
    private String developer;

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }
    /**
     * @return the message
     */
    public String getMessage()
    {
        return message;
    }


    /**
     * @param message the message to set
     */
    public void setMessage(String message)
    {
        this.message = message;
    }


    public String execute() throws Exception
    {
        setMessage(MESSAGE);
//        return SUCCESS;
        return "success";
    }

    public String myPage(){
        return "mypage";
    }

    public String add1(){
        HttpServletRequest request = ServletActionContext.getRequest();
        return "add1";
    }

    public String add2(){
        return "add2";
    }

    public String form(){
        return "form";
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForm(){
        return "getform";
    }
}
