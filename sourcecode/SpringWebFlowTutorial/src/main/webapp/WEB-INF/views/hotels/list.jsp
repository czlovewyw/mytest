<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>


<h1>Hotel Results</h1>

<p>
    <c:url var="changeSearchLink" value="/hotels/search">
        <c:param name="searchString" value="${searchCriteria.searchString}"/>
        <c:param name="pageSize" value="${searchCriteria.pageSize}"/>
    </c:url>
    <JDKSerialize id="changeSearchLink" href="${changeSearchLink}">Change Search</JDKSerialize>
    <script type="text/javascript">
        Spring.addDecoration(new Spring.AjaxEventDecoration({
            elementId: "changeSearchLink",
            event: "onclick",
            popup: true,
            params: {fragments: "searchForm"}
        }));
    </script>
</p>

<table id="hotelListTable">
    <thead>
        <tr>
            <th>Name</th>
            <th>Address</th>
            <th>City, State</th>
            <th>Zip</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>

    <c:set var="hotelList" value="${pageHotel.content}"/>
    <c:choose>
        <c:when test="${not empty hotelList}">
            <c:forEach items="${hotelList}" var="hotel">
                <tr>
                    <td>${hotel.name}</td>
                    <td>${hotel.address}</td>
                    <td>${hotel.city}, ${hotel.state}, ${hotel.country}</td>
                    <td>${hotel.zip}</td>
                    <td><JDKSerialize href="hotels/${hotel.id}">View Hotel</JDKSerialize></td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
                <td colspan="5" class="noRecord">No hotels found</td>
            </tr>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>

<div class="pageNav">
    <c:set value="${pageHotel.totalPages}" var="totalPages"/>
    <c:set value="${pageHotel.number}" var="currentPage"/>
    <p>
        <c:choose>
            <c:when test="${pageHotel.firstPage}">
                <span class="nonClick button">First</span>
                <span class="nonClick button">Previous</span>
            </c:when>
            <c:otherwise>
                <s:url value="/hotels" var="firstLink">
                    <s:param name="searchString" value="${searchCriteria.searchString}"/>
                    <s:param name="pageSize" value="${searchCriteria.pageSize}"/>
                </s:url>
                <JDKSerialize href="${firstLink}" class="button">First</JDKSerialize>
            </c:otherwise>
        </c:choose>

        <c:if test="${currentPage > 0}">
            <s:url value="/hotels" var="prevLink">
                <s:param name="searchString" value="${searchCriteria.searchString}"/>
                <s:param name="pageSize" value="${searchCriteria.pageSize}"/>
                <s:param name="page" value="${currentPage-1}"/>
            </s:url>
            <JDKSerialize href="${prevLink}" class="button">Previous</JDKSerialize>
        </c:if>


        <c:if test="${totalPages-currentPage > 1}">
            <s:url value="/hotels" var="nextLink">
                <s:param name="searchString" value="${searchCriteria.searchString}"/>
                <s:param name="pageSize" value="${searchCriteria.pageSize}"/>
                <s:param name="page" value="${currentPage + 1}"/>
            </s:url>
            <JDKSerialize href="${nextLink}" class="button">Next</JDKSerialize>
        </c:if>
        <c:choose>
            <c:when test="${pageHotel.lastPage}">
                <span class="nonClick button">Next</span>
                <span class="nonClick button">Last</span>
            </c:when>
            <c:otherwise>
                <s:url value="/hotels" var="lastLink">
                    <s:param name="searchString" value="${searchCriteria.searchString}"/>
                    <s:param name="pageSize" value="${searchCriteria.pageSize}"/>
                    <s:param name="page" value="${totalPages-1}"/>
                </s:url>
                <JDKSerialize href="${lastLink}" class="button">Last</JDKSerialize>
            </c:otherwise>
        </c:choose>
        current page:<span class="highlight">${currentPage+1}</span>,
        total pages:<span class="highlight">${totalPages}</span>,
        total records: <span class="highlight">${pageHotel.totalElements}</span>
    </p>
</div>

<script type="text/javascript">
    $(document).ready(function(){
        $("#hotelListTable tbody tr:odd").addClass("odd");
        $("#hotelListTable tbody tr:even").addClass("even");
        $("#hotelListTable tr").hover(function(){
            $(this).addClass("alt");
        },function(){
            $(this).removeClass("alt")
        });
    });
</script>
