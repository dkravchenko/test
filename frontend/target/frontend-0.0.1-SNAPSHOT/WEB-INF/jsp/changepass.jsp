<%@page import="net.sam_solutions.courses.dkrauchanka.dto.ReportDTO" %>
<%@page import="net.sam_solutions.courses.dkrauchanka.dto.UserDTO" %>
<%@page import="net.sam_solutions.courses.dkrauchanka.dto.TaskDTO" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Iterator" %>
<%@page import="net.sam_solutions.courses.dkrauchanka.tags.Paginator" %>
<%@include file="header.jsp" %>
<div style="float:left; margin:10px"> 
    <h1><user:locale value="changepass.header"/></h1>
    <user:locale value="changepass.info"/><br/>
    <font color="#FF0000">
    <%
        String error = (String)request.getAttribute("error");
        if(error != null && !error.equals(""))
            out.print(error);
    %>
    </font>
    <form action="<%= request.getContextPath() %>/change/pass" method="post">
        <table>
            <tr>
                <td>
                    <user:locale value="changepass.pass"/>
                </td>
                <td>
                    <input type="password" name="pass"/>
                </td>
            </tr>
            <tr>
                <td>
                    <user:locale value="changepass.confirm"/>
                </td>
                <td>
                    <input type="password" name="confirm"/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="hidden" name="login" value="<%= (String)request.getAttribute("login") %>"/>
                </td>
                <td>
                    <input type="submit" name="submit" value="<user:locale value="changepass.submit"/>" style="float:right"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<%@include file="footer.jsp" %>
