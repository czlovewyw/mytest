<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Spring Travel: Spring MVC and Web Flow Reference Application</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/dijit/themes/tundra/tundra.css" />" />
    <link rel="stylesheet" type="text/css" href="<s:url value="/resources/css/main.css" />" />
    <!--[if lte IE 6]>
    <link rel="stylesheet" type="text/css" href="<s:url value="/resources/css/ie6.css" />" />
    <![endif]-->
    <script type="text/javascript" src="<c:url value="/resources/dojo/dojo.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/spring/Spring.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/spring/Spring-Dojo.js" />"></script>

    <script type="text/javascript" src="<s:url value="/resources/js/jquery-1.7.1.min.js" />"></script>
    <script type="text/javascript" src="<s:url value="/resources/js/jquery.validate.min.js" />"></script>
</head>
<body class="tundra">
<div id="wrapper">
    <div id="header">
        <div id="topbar">
            <p>
                <sec:authorize access="hasRole('ROLE_USER')">
                    <c:if test="${pageContext.request.userPrincipal != null}">
						Welcome, ${pageContext.request.userPrincipal.name} |
					</c:if>
					<JDKSerialize href="<c:url value="/logout" />">Logout</JDKSerialize> |
                    <JDKSerialize href="<c:url value="/sec/users/changePassword" />">Change Password</JDKSerialize>
                </sec:authorize>
                <sec:authorize access="anonymous">
                    <JDKSerialize href="<c:url value="/login" />">Login</JDKSerialize>
                </sec:authorize>
            </p>
        </div>

        <div id="logo">
             <p>
                <JDKSerialize href="<s:url value="/" />"><img src="<s:url value="/resources/images/header.jpg" />" alt="generic hotel" /></JDKSerialize>
            </p>
        </div>
    </div>

    <div id="content">
        <div id="primary">
            <tiles:insertAttribute name="main" />
        </div>

        <div id="secondary">
            <p>
                <JDKSerialize href="http://www.thespringexperience.com">
					<img src="<s:url value="/resources/images/diplomat.jpg" />" alt="generic hotel" />
				</JDKSerialize>
            </p>
            <p class="center">
				<JDKSerialize href="http://www.thespringexperience.com">
					<img src="<s:url value="/resources/images/springone2gx.jpeg" />" alt="SpringOne 2GX" />
				</JDKSerialize>
			</p>
        </div>
    </div>

    <div id="footer">
        <JDKSerialize href="http://www.springframework.org">
			<img src="<s:url value="/resources/images/powered-by-spring.png" />" alt="Powered by Spring" />
		</JDKSerialize>
    </div>
</div>
</body>
</html>