<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="span5">
	<p>Valid username/passwords are:</p>
	<ul>
		<li>keith/melbourne</li>
		<li>erwin/leuven</li>
		<li>jeremy/atlanta</li>
		<li>scott/rochester</li>
	</ul>
</div>

<div class="span10 last">
    <div class="error">
        <c:if test="${not empty param.error}">
           <p>Your username or password was not correct,try again. </p>
        </c:if>
    </div>
    <form id="loginForm" action="<s:url value="/loginProcess" />" method="post">
        <fieldset>
            <legend class="label">Login Information</legend>
            <p>
                <label for="j_username" class="label">User:</label>
                <br />
                <input type="text" name="j_username" id="j_username" />
            </p>

            <p>
                <label for="j_password" class="label">Password:</label>
                <br />
                <input type="password" name="j_password" id="j_password" />
            </p>

            <p>
                <input type="checkbox" name="_remember_me" id="remember_me" />
                <label for="remember_me" class="label">Don't ask for my password for two weeks:</label>
            </p>

            <p>
                <button id="submit" type="submit">Login</button>
            </p>
        </fieldset>
    <form>
</div>

<script type="text/javascript">
    jQuery(document).ready(function(){
        var validator = jQuery("#loginForm").validate({
            rules: {
                j_username: "required",
                j_password: "required"
            },
            messages: {
                j_username: "请输入用户名",
                j_password: "请输入密码"
            }
        });
    });
</script>