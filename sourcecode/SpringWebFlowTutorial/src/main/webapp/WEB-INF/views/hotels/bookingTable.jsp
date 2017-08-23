<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div id="bookingDiv">
    <security:authorize access="hasRole('ROLE_USER')">
        <h2>Current Hotel Bookings</h2>

        <c:set var="bookingList" value="${bookingPage.content}"/>
        <c:choose>
            <c:when test="${not empty bookingList}">
                <table id="bookingTable">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Address</th>
                        <th>City, State</th>
                        <th>Check in Date</th>
                        <th>Check out Date</th>
                        <th>Confirmation Number</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${bookingList}" var="booking">
                        <tr>
                            <td>${booking.hotel.name}</td>
                            <td>${booking.hotel.address}</td>
                            <td>${booking.hotel.city}, ${booking.hotel.state}</td>
                            <td>${booking.checkinDate}</td>
                            <td>${booking.checkoutDate}</td>
                            <td>${booking.id}</td>
                            <td>
                                <s:url value="/bookings/{id}" var="deleteBookingUrl">
                                    <s:param name="id" value="${booking.id}"/>
                                </s:url>
                                <sf:form action="${deleteBookingUrl}" method="delete">
                                    <input type="submit" value="delete" class="button"/>
                                </sf:form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>No bookings found</p>
            </c:otherwise>
        </c:choose>


        <div class="pageNav">
            <c:set value="${bookingPage.number}" var="currentPage"/>
            <c:set value="${bookingPage.totalPages}" var="totalPages"/>

            <c:if test="${currentPage > 0}">
                <s:url value="/hotels/search" var="prevLink">
                    <s:param name="pageSize" value="${searchCriteria.pageSize}"/>
                    <s:param name="page" value="${currentPage-1}"/>
                </s:url>

                <JDKSerialize id="prevLink" href="${prevLink}" class="button">Previous</JDKSerialize>

                <script type="text/javascript">
                    Spring.addDecoration(new Spring.AjaxEventDecoration({
                        elementId: "prevLink",
                        event: "onclick",
                        params: {fragments: "bookingTable"}
                    }));
                </script>
            </c:if>

            <c:if test="${totalPages-currentPage > 1}">
                <s:url value="/hotels/search" var="nextLink">
                    <s:param name="pageSize" value="${searchCriteria.pageSize}"/>
                    <s:param name="page" value="${currentPage + 1}"/>
                </s:url>
                <JDKSerialize id="nextLink" href="${nextLink}" class="button">Next</JDKSerialize>

                <script type="text/javascript">
                    Spring.addDecoration(new Spring.AjaxEventDecoration({
                        elementId: "nextLink",
                        event: "onclick",
                        params: {fragments: "bookingTable"}
                    }));
                </script>
            </c:if>
        </div>
    </security:authorize>
</div>

<script type="text/javascript">
    $(document).ready(function(){
        $("#bookingTable tr").hover(function(){
            $(this).addClass("alt");
        },function(){
            $(this).removeClass("alt")
        });
    });
</script>
