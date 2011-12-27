<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%= request.getAttribute("errorCode") %><br/>
<%= request.getAttribute("errorText") %><br/><br/>
<%
	StackTraceElement[] el = (StackTraceElement[])request.getAttribute("stackTrace");
	for(int i=0;i<el.length;i++)
		out.println(el[i].toString());
%>
</body>
</html>