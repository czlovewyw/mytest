package org.leochen.samples.web.controllers.hotels;

import org.springframework.webflow.core.FlowException;
import org.springframework.webflow.execution.FlowExecutionOutcome;
import org.springframework.webflow.execution.repository.NoSuchFlowExecutionException;
import org.springframework.webflow.mvc.servlet.AbstractFlowHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.annotation.XmlElement;

/**
 * User: leochen
 * Date: 11-12-14
 * Time: 下午12:29
 */
public class BookingFlowHandler extends AbstractFlowHandler{
    private static final String DEFAULT_URL = "/hotels/search";

    @Override
    public String handleExecutionOutcome(FlowExecutionOutcome outcome, HttpServletRequest request, HttpServletResponse response) {
        return DEFAULT_URL;
    }

    @Override
    public String handleException(FlowException e, HttpServletRequest request, HttpServletResponse response) {
        if(e instanceof NoSuchFlowExecutionException){
            return DEFAULT_URL;
        }else {
            throw e;
        }

    }
}
