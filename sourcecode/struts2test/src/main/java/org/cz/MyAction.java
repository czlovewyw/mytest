package org.cz;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * Created by chenzhe8 on 2017/3/10.
 */
public class MyAction implements ServletRequestAware,ServletResponseAware,ServletContextAware {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private ServletContext servletContext;
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public void setServletContext(ServletContext context) {
        this.servletContext = context;
    }
}
