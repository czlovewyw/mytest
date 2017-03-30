<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div id="bookingForm">
    <div class="first">
        <h3>${booking.hotel.name}</h3>

        <address>
            ${booking.hotel.address}
            <br/> <br/>
            ${booking.hotel.city}, ${booking.hotel.state}, ${booking.hotel.zip}
            <br/>  <br/>
            ${booking.hotel.country}
        </address>
        <p>
            Nightly rate: <s:bind path="booking.hotel.price">${status.value}</s:bind>
        </p>
    </div>

    <div class="second">
        <sf:form modelAttribute="booking" action="${flowExecutionUrl}">
            <fieldset>
                <legend class="label">Book Hotel</legend>
                <p>
                    <label for="checkinDate" class="float">Check In:</label>
                    <sf:input path="checkinDate" />
                    <sf:errors path="checkinDate" cssClass="error" />
                    <script type="text/javascript">
                        Spring.addDecoration(new Spring.ElementDecoration({
                            elementId:"checkinDate",
                            widgetType: "dijit.form.DateTextBox",
                            widgetAttrs: {datePattern: "yyyy-MM-dd",required: true}
                        }));
                    </script>
                </p>
                <p>
                    <label for="checkoutDate" class="float">Check Out:</label>
                    <sf:input path="checkoutDate" />
                    <sf:errors path="checkoutDate" cssClass="error"/>
                    <script type="text/javascript">
                        Spring.addDecoration(new Spring.ElementDecoration({
                            elementId:"checkoutDate",
                            widgetType: "dijit.form.DateTextBox",
                            widgetAttrs: {datePattern: "yyyy-MM-dd",required: true}
                        }));
                    </script>
                </p>
                <p>
                    <label for="beds" class="float">Room Preference:</label>
                    <sf:select path="beds">
                        <sf:option value="1" label="One king-size bed"/>
                        <sf:option value="2" label="Two double beds"/>
                        <sf:option value="3" label="Three beds"/>
                    </sf:select>
                </p>
                <p>
                    <label for="smoking" class="float">Smoking Preference:</label>
                    <sf:radiobutton path="smoking" value="true" label="Smoking"/>
                    <sf:radiobutton path="smoking" value="false" label="Non Smoking"/>
                </p>
                <p>
                    <label for="amenities" class="float">Amenities:</label>
                    <sf:checkbox path="amenities" value="OCEAN_VIEW" label="Ocean View"/>
                    <sf:checkbox path="amenities" value="LATE_CHECKOUT" label="Late Checkout" />
                    <sf:checkbox path="amenities" value="MINIBAR" label="Minibar" />
                </p>
                <p>
                    <label for="creditCard" class="float">Credit Card #:</label>
                    <sf:input path="creditCard" />
                    <sf:errors path="creditCard" cssClass="error" />
                </p>
                <p>
                    <label for="creditCardName" class="float">Credit Card Name:</label>
                    <sf:input path="creditCardName" maxlength="50" />
                    <sf:errors path="creditCardName" cssClass="error" />
                </p>
                <p>
                    <label for="creditCardExpiryMonth" class="float">Expiration Date:</label>
                    <sf:select path="creditCardExpiryMonth">
                        <c:forEach begin="1" end="12" var="month">
                            <sf:option value="${month}" label="${month}" />
                        </c:forEach>
                    </sf:select>
                    <sf:select path="creditCardExpiryYear">
                            <sf:option value="2008" label="2008"/>
                            <sf:option value="2009" label="2009"/>
                            <sf:option value="2010" label="2010"/>
                            <sf:option value="2011" label="2011"/>
                        <sf:option value="2012" label="2012"/>
                    </sf:select>
                    <sf:errors path="creditCardExpiryMonth" cssClass="error" />
                    <sf:errors path="creditCardExpiryYear" cssClass="error" />
                </p>
                <p>
                    <input type="submit" name="_eventId_submit" value="Submit" class="button" />
                    <input type="submit" name="_eventId_cancel" value="Cancel" class="button" />
                </p>
            </fieldset>
        </sf:form>
    </div>
    <br class="clear"/>
</div>