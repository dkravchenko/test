<%@page contentType="text/html" pageEncoding="windows-1251"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="login" method="get">
            <input type="text" name="login"/><br/>
            <input type="password" name="pass"/><br/>
            <input type="submit" name="submit" value="send"/><br/>
            <%= request.getParameter("login")%>
            <%= request.getParameter("pass")%>
        </form>
    </body>
</html>
