<%@page import="net.sam_solutions.courses.dkrauchanka.dto.UserDTO" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Iterator" %>
<%@page import="net.sam_solutions.courses.dkrauchanka.tags.Paginator" %>
<%@include file="header.jsp" %>
<div style="float:left; margin:10px"> 
    <h1><user:locale value="user.header"/></h1>
    <table>
        <tr>
            <td>
                <user:locale value="user.login"/>
            </td>
            <td>
                <user:locale value="user.fn"/>
            </td>
            <td>
                <user:locale value="user.ln"/>
            </td>
            <td>
                <user:locale value="all.remove"/>
            </td>
            <td>
                <user:locale value="all.update"/>
            </td>
        </tr>
        <%
            List<UserDTO> list = (List<UserDTO>)request.getAttribute("userList");
            request.removeAttribute("userList");
            Iterator<UserDTO> iter = list.iterator();
            while(iter.hasNext()){
                UserDTO userDto = iter.next();       
        %>
        <tr>
            <td>
                <%= userDto.getLogin() %>
            </td>
            <td>
                <%= userDto.getFirstName() %>
            </td>
            <td>
                <%= userDto.getLastName() %>
            </td>
            <td>
                <a href="<%= request.getContextPath() %>/user?page=<%= request.getAttribute("currentPage") %>&login=<%= userDto.getLogin() %>&action=delete"><user:locale value="all.remove"/></a>
            </td>
            <td>
                <a href="<%= request.getContextPath() %>/user?page=<%= request.getAttribute("currentPage") %>&login=<%= userDto.getLogin() %>&action=update"><user:locale value="all.update"/></a>
            </td>
        </tr>
        <%
               }
        %>
    </table>
        <%= Paginator.generateString((Integer)request.getAttribute("pages"), (Integer)request.getAttribute("currentPage"), request.getContextPath()+"/user",null,null) %><br/><br/>
        <font color="#FF0000">
        <%
            String error = (String)request.getAttribute("error");
            if(error != null && !error.equals("")){
                out.print(error);
            }
        %><br/>
        </font>
        <form action="<%= request.getContextPath() %>/user" method="post">
            <table>
                <%
                    UserDTO userDto = (UserDTO)request.getAttribute("userToUpdate");
                    String login = "";
                    String firstName = "";
                    String lastName = "";
                    String action = "add";
                    if(userDto != null){
                        login = userDto.getLogin();
                        firstName = userDto.getFirstName();
                        lastName = userDto.getLastName();
                        if(error == null || request.getParameter("action_form").equals("update")){
                            action = "update";
                        }
                    }
                %>
                <tr>
                    <td>
                        <user:locale value="user.login"/>
                    </td>
                    <td>
                        <% 
                            if((action.equals("update") && error == null) || (request.getParameter("action_form") != null && request.getParameter("action_form").equals("update") && userDto != null)){
                        %>
                            <input type="text" name="user_l" value="<%= login %>" disabled/>
                            <input type="hidden" name ="user_login" value="<%= login %>"/>
                            <%
                                                           }
                                       else {
                            %>
                            <input type="text" name="user_login" value="<%= login %>"/>
                            <%
                                                           }
                            %>
                    </td>
                </tr>
                <tr>
                    <td>
                        <user:locale value="user.fn"/>
                    </td>
                    <td>
                        <input type="text" name="user_fn" value="<%= firstName %>"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <user:locale value="user.ln"/>
                    </td>
                    <td>
                        <input type="text" name="user_ln" value="<%= lastName %>"/>  
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="hidden" name="action_form" value="<%= action %>"/>
                    </td>
                    <td>
                        <% 
                            if(action.equals("update")){
                        %>
                        <input type="submit" name="user_submit" value="<user:locale value="all.update"/>"/>
                            <%
                                                           }
                                       else {
                            %>
                            <input type="submit" name="user_submit" value="<user:locale value="all.add"/>"/>
                            <%
                                                           }
                            %>
                    </td>
                </tr>
            </table>
        </form>

</div>
<%@include file="footer.jsp" %>