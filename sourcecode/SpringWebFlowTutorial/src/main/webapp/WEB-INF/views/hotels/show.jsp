<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<h1>${hotel.name}</h1>

<address>
	${hotel.address}
	<br />
	${hotel.city}, ${hotel.state}, ${hotel.zip}
	<br />
	${hotel.country}
</address>
<p>Nightly Rate: ${hotel.price} $</p>

<form action="<s:url value="/hotels/booking?mode=embedded" />" method="get">
    <input type="hidden" name="hotelId" value="${hotel.id}" />
    <input type="submit" value="Book Hotel" class="button" />
</form>