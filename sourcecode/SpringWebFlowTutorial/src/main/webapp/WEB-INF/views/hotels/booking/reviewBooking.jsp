<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>


<div id="bookingForm">
    <div class="first">
        <h3>${booking.hotel.name}</h3>

        <address>
            ${booking.hotel.address}
            <br/>
            ${booking.hotel.city}, ${booking.hotel.state}, ${booking.hotel.zip}
            <br/>
            ${booking.hotel.country}
        </address>
    </div>

    <div class="second">
        <sf:form id="confirmForm" modelAttribute="booking" action="${flowExecutionUrl}">
            <fieldset>
                <legend class="label">Confirm Booking Details</legend>

                <p>
                    <label class="float">Check In:</label>
                    <s:bind path="checkinDate">${status.value}</s:bind>
                </p>
                <p>
                    <label class="float">Check Out:</label>
                    <s:bind path="checkoutDate">${status.value}</s:bind>
                </p>
                <p>
                    <label class="float">Number of Nights:</label>
                    <s:bind path="nights">${status.value}</s:bind>
                </p>
                <p>
                    <label class="float">Total Payment:</label>
                    <s:bind path="total">${status.value}</s:bind>
                </p>
                <p>
                    <label class="float">Credit Card #:</label>
                    ${booking.creditCard}
                </p>
                <p>
                    <input type="submit" name="_eventId_confirm" value="Confirm" class="button" />
                    <input type="submit" name="_eventId_revise" value="Revise" class="button" id="revise" />
                    <input type="submit" name="_eventId_cancel" value="Cancel" class="button" />
                </p>

                <script type="text/javascript">
                    Spring.addDecoration(new Spring.AjaxEventDecoration({
                        elementId: "revise",
                        event: "onclick",
                        formId: "confirmForm"
                    }));
                </script>

            </fieldset>
        </sf:form>
    </div>
</div>