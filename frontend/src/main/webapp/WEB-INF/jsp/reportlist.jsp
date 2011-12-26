<%@page import="net.sam_solutions.courses.dkrauchanka.dto.ReportDTO" %>
<%@page import="net.sam_solutions.courses.dkrauchanka.dto.UserDTO" %>
<%@page import="net.sam_solutions.courses.dkrauchanka.dto.TaskDTO" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Iterator" %>
<%@page import="net.sam_solutions.courses.dkrauchanka.tags.Paginator" %>
<%@include file="header.jsp" %>
<div style="float:left; margin:10px"> 
    <h1><user:locale value="report.header"/></h1>
    <%
        List<ReportDTO> list = (List<ReportDTO>)request.getAttribute("reportList");
        request.removeAttribute("reportList");
        if(list != null && list.size() > 0)
                       {
        Iterator<ReportDTO> iter = list.iterator();
    %>
    <table>
        <tr>
            <td>
                <user:locale value="report.date"/>
            </td>
            <td>
                <user:locale value="report.workingHours"/>
            </td>
            <td>
                <user:locale value="report.doneHours"/>
            </td>
            <td>
                <user:locale value="report.user"/>
            </td>
            <td>
                <user:locale value="report.tasks"/>
            </td>
        </tr>
        <%
            
            while(iter.hasNext()){
                ReportDTO reportDto = iter.next();       
        %>
        <tr>
            <td>
                <%= reportDto.getDateOfReport().toString() %>
            </td>
            <td>
                <%= reportDto.getWorkingHours() %>
            </td>
            <td>
                <%= reportDto.getDoneHours() %>
            </td>
            <td>
                <%= reportDto.getUser() %>
            </td>
            <td>
                <%= reportDto.getTasks() %>
            </td>
        </tr>
        <%
               }
        %>
        </table>
        <%
            String filterName = null;
            String filterValue = null;
            if(request.getParameter("filter_user") != null && !request.getParameter("filter_user").equals("")){
                filterName = "filter_user";
                filterValue = request.getParameter("filter_user");
            }
            else if(request.getParameter("filter_task") != null && !request.getParameter("filter_task").equals("")) {
                filterName = "filter_task";
                filterValue = request.getParameter("filter_task");
            }
        %>
        <%= Paginator.generateString((Integer)request.getAttribute("pages"), (Integer)request.getAttribute("currentPage"), request.getContextPath()+"/report/list", filterName,filterValue) %><br/><br/>
        <%
               }
        %>
        </div>
        <div style="float:right">
        <h4><user:locale value="all.filter"/></h4>
        <form action="<%= request.getContextPath() %>/report/list" method="post">
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
                    <user:locale value="filter.task"/>
                </td>
                <td>
                    <select name="filter_task" style="width:150px">
                        <option value="0">...</option>
                        <%
                            List<TaskDTO> listTask = (List<TaskDTO>)request.getAttribute("taskList");
                            String filterTask = request.getParameter("filter_task");
                            if(listTask != null){
                            Iterator<TaskDTO> iterTask = listTask.iterator(); 
                            while(iterTask.hasNext()){
                                TaskDTO taskDto = iterTask.next();
                        %>
                        <option value="<%= taskDto.getId() %>" <% if(filterTask != null && filterTask.equals(taskDto.getId())) out.print("selected"); %>><%= taskDto.getTitle() %></option>
                        
                        <%
                                                   }}
                        %>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <user:locale value="filter.date"/>
                </td>
                <td>
                    <input type="text" name="filter_date" style="width:150px"/>
                </td>
            </tr>
            <%
                String error = (String)request.getAttribute("error");
                if(error != null && !error.equals("")){
            %>
            <tr>
                <td></td>
                <td><%= error %></td>
            </tr>
            <%
                       }
                %>
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