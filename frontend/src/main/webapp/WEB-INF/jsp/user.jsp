<%@page import="java.util.Locale"%>
<%@page contentType="text/html" pageEncoding="windows-1251"%>
<%@page import="java.util.List" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.ResourceBundle" %>
<%@page import="net.sam_solutions.courses.dkrauchanka.domain.User" %>
<%@page import="net.sam_solutions.courses.dkrauchanka.dto.UserDTO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            ResourceBundle resourceBundle = ResourceBundle.getBundle("text", Locale.getDefault()); 
        %>
        <h1><%= resourceBundle.getString("user.header") %></h1>
        <table>
            <tr>
                <td><%= resourceBundle.getString("user.fn") %></td><td><%= resourceBundle.getString("user.ln") %></td><td><%= resourceBundle.getString("user.remove") %></td><td><%= resourceBundle.getString("user.update") %></td>
            </tr>
            <% 
                List<UserDTO> list = (List<UserDTO>)request.getAttribute("userList");
                request.removeAttribute("userList");
                Iterator<UserDTO> iter = list.iterator();
                while(iter.hasNext()){
                    UserDTO userDto = iter.next();
                    %>
                    <tr><td><%= userDto.getFirstName() %></td><td><%= userDto.getLastName() %></td><td><a href="<%= request.getContextPath() %>/user/staff?login=<%= userDto.getLogin() %>&action=delete"><%= resourceBundle.getString("user.remove") %></a></td><td><a href="<%= request.getContextPath() %>/user/staff?login=<%= userDto.getLogin() %>&action=update"><%= resourceBundle.getString("user.update") %></a></td></tr>
                    <%
                }
            %>
        </table><br/><br/>
        <%
            String action = request.getParameter("action");
            String login;
            String fn;
            String ln;
            String button;
            if(action != null && action.equals("update")){
                login = request.getParameter("login");
                UserDTO userToUpdate = (UserDTO)request.getAttribute("userToUpdate");
                request.removeAttribute("userToUpdate");
                login = userToUpdate.getLogin();
                fn = userToUpdate.getFirstName();
                ln = userToUpdate.getLastName();
                button = resourceBundle.getString("button.update");
            }
            else{
                action = "add";
                login = "";
                fn = "";
                ln = "";
                button = resourceBundle.getString("button.add");
            }
        %>
        <form action="<%= request.getContextPath() %>/user/staff" method="post">
            <table>
                <tr>
                    <td><%= resourceBundle.getString("user.login") %></td><td><input type="text" name="login" value ="<%= login %>" <% if(!login.equals("")) { %>disabled<% } %>/></td>
                </tr>
                <tr>
                    <td><%= resourceBundle.getString("user.fn") %></td><td><input type="text" name="fn" value ="<%= fn %>"/></td>
                </tr>
                <tr>
                    <td><%= resourceBundle.getString("user.ln") %></td><td><input type="text" name="ln" value ="<%= ln %>"/></td>
                </tr>
                <tr>
                    <td><input type ="submit" name="<%= action %>" value="<%= button %>"/></td><td></td> 
                </tr>    
            </table>
        </form>
    </body>
</html>
