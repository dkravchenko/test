<%@page import="net.sam_solutions.courses.dkrauchanka.dto.ReportDTO" %>
<%@page import="net.sam_solutions.courses.dkrauchanka.dto.UserDTO" %>
<%@page import="net.sam_solutions.courses.dkrauchanka.dto.TaskDTO" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Iterator" %>
<%@page import="net.sam_solutions.courses.dkrauchanka.tags.Paginator" %>
<%@include file="header.jsp" %>
<div style="float:left; margin:10px"> 
    <h1><user:locale value="tasklist.header"/></h1>
    <%
        List<TaskDTO> list = (List<TaskDTO>)request.getAttribute("taskList");
        request.removeAttribute("taskList");
        if(list != null && list.size() > 0)
                       {
            Iterator<TaskDTO> iter = list.iterator();
    %>
    <table>
        <tr>
            <td>
                <user:locale value="task.title"/>
            </td>
            <td>
                <user:locale value="task.text"/>
            </td>
            <td>
                <user:locale value="task.hours"/>
            </td>
            <td>
                <user:locale value="task.status"/>
            </td>
            <td>
                <user:locale value="task.user"/>
            </td>
        </tr>
        <%
            
            while(iter.hasNext()){
                TaskDTO taskDto = iter.next();       
        %>
        <tr>
            <td>
                <%= taskDto.getTitle() %>
            </td>
            <td>
                <%= taskDto.getText() %>
            </td>
            <td>
                <%= taskDto.getHoursToDo() %>
            </td>
            <td>
                <%= taskDto.getStatus() %>
            </td>
            <td>
                <%= taskDto.getUser() %>
            </td>
        </tr>
        <%
               }
        %>
        </table>
        <%
            String filterValue = null;
            if(request.getParameter("filter_user") != null){
                filterValue = request.getParameter("filter_user");
            }
        %>
        <%= Paginator.generateString((Integer)request.getAttribute("pages"), (Integer)request.getAttribute("currentPage"), request.getContextPath()+"/task/list","filter_user",filterValue) %><br/><br/>
        <%
               }
        %>
        </div>
        <div style="float:right">
        <h4><user:locale value="all.filter"/></h4>
        <form action="<%= request.getContextPath() %>/task/list" method="post">
        <table>
            <tr>
                <td>
                    <user:locale value="filter.user"/>
                </td>
                <td>
                    <select name="filter_user" style="width:150px">
                        <option value="0">...</option>
                        <%
                            List<UserDTO> listUser = (List<UserDTO>)request.getAttribute("userList");
                            String filterUser = request.getParameter("filter_user");
                            if(listUser != null){
                            Iterator<UserDTO> iterUser = listUser.iterator(); 
                            while(iterUser.hasNext()){
                                UserDTO userDto = iterUser.next();
                        %>
                        <option value="<%= userDto.getLogin() %>" <% if(filterUser != null && filterUser.equals(userDto.getLogin())) out.print("selected"); %>><%= userDto.getFirstName()+" "+userDto.getLastName() %></option>
                        <%
                                                   }}
                        %>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    
                </td>
                <td>
                    <input type="submit" name="submit" value="<user:locale value="filter.submit"/>" style="float:right"/>
                </td>
            </tr>
        </table>
        </form>
        </div>
<%@include file="footer.jsp" %>
