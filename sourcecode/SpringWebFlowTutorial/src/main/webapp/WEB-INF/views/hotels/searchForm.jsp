<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>


<div class="searchDiv">
    <h1>Search Hotels</h1>
    <s:url var="hotelsUrl" value="/hotels" />
    <sf:form action="${hotelsUrl}" method="get" modelAttribute="searchCriteria">
        <fieldset>
            <p class="span8">
                <label for="searchString" class="label">Search String:</label>
                <sf:input path="searchString" id="searchString" title="Search hotels by name, address, city, or zip." />
                <script type="text/javascript">
                    Spring.addDecoration(new Spring.ElementDecoration({
                    elementId: "searchString",
                    widgetType: "dijit.form.ValidationTextBox",
                    widgetAttrs: { promptMessage : "Search hotels by name, address, city, or zip." }}));
                </script>
            </p>

            <p class="span6">
                <label for="pageSize" class="label">Maximum results:</label>
                <sf:select path="pageSize" id="pageSize">
                    <sf:option value="5" label="5"/>
                    <sf:option value="10" label="10"/>
                    <sf:option value="15" label="15"/>
                </sf:select>
            </p>

            <p class="span3 last">
                <input type="submit" value="Find Hotels" class="button" />
            </p>
        </fieldset>
    </sf:form>
</div>




