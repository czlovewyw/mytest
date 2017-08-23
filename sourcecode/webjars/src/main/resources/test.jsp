<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="java.lang.*" %>
<%@ page import="java.io.IOException" %>
<%
    try {
        response.addHeader("WWW-Authenticate","Basic realm=/"Tomcat Manager Application/"");
        response.sendError(401,"Unauthorized");
    } catch (IOException e) {
        e.printStackTrace();
    }
%>