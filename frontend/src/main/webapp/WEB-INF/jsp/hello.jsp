<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="net.sam_solutions.courses.dkrauchanka.framework.SessionUser" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <% 
        SessionUser user = (SessionUser)session.getAttribute("user");
        out.println(user.getFirstName()+" "+user.getLastName()+" "+user.getRole());
    %><br/><br/>
</body>
</html>