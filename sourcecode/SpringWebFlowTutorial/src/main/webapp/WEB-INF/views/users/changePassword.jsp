<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<sf:form id="changePasswordForm" modelAttribute="changePasswordValidationBean" method="post">
    <fieldset>
        <legend class="label">Change Password</legend>
        <p>
            <label for="oldPassword" class="label">Old password:</label> <br/>
            <sf:password path="oldPassword" />
            <sf:errors id="oldPassword" path="oldPassword" cssClass="error"/>
        </p>

        <p>
            <label for="newPassword" class="label">New password:</label>  <br/>
            <sf:password path="newPassword" />
            <sf:errors id="newPassword" path="newPassword" cssClass="error"/>
        </p>

        <p>
            <label for="confirmNewPassword" class="label">Confirm new password:</label> <br/>
            <sf:password path="confirmNewPassword" />
            <sf:errors id="confirmNewPassword" path="confirmNewPassword" cssClass="error"/>
        </p>
        <p>
            <input type="submit" value="Change Password" class="button" />
        </p>
    </fieldset>
</sf:form>

<script type="text/javascript">
    jQuery(document).ready(function(){
        var validator = jQuery("#changePasswordForm").validate({
            rules: {
                oldPassword: "required",
                newPassword: {
                    required: true,
                    minlength: 5
                },
                confirmNewPassword: {
                    required: true,
                    minlength: 5,
                    equalTo: "#newPassword"
                }
            },
            messages: {
                oldPassword: "请输入原始密码",
                newPassword: {
                    required: "请输入新密码",
                    minlength: jQuery.format("密码最小长度为{0}")
                },
                confirmNewPassword:{
                    required: "请再次输入新密码",
                    minlength: jQuery.format("密码最小长度为{0}位"),
                    equalTo: "两次密码输入不一致"
                }
            }
        });
    });
</script>