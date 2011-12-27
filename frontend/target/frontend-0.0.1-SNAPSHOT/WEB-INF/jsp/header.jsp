<%@taglib uri="/tlds/usertaglib.tld" prefix="user"%>
<%@page import="net.sam_solutions.courses.dkrauchanka.framework.SessionUser" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script type="text/javascript" src="<%= request.getContextPath() %>/script.js">
        
    </script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
    <% 
        SessionUser user = (SessionUser)session.getAttribute("user");
        if(user.getRole().equals("user")){
    %>
    <%@include file="user_menu.jsp" %>
    <% }else {%>
    <%@include file="admin_menu.jsp" %>
    <% } %>
