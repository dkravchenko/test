<%@page import="net.sam_solutions.courses.dkrauchanka.dto.ReportDTO" %>
<%@page import="net.sam_solutions.courses.dkrauchanka.dto.UserDTO" %>
<%@page import="net.sam_solutions.courses.dkrauchanka.dto.TaskDTO" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Iterator" %>
<%@page import="net.sam_solutions.courses.dkrauchanka.tags.Paginator" %>
<%@include file="header.jsp" %>
<div style="float:left; margin:10px"> 
    <h1><user:locale value="taskrule.header"/></h1>
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
            <td>
                <user:locale value="all.remove"/>
            </td>
            <td>
                <user:locale value="all.update"/>
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
            <td>
                <a href="<%= request.getContextPath() %>/task/rule?page=<%= request.getAttribute("currentPage") %>&task_id=<%= taskDto.getId() %>&action=delete"><user:locale value="all.remove"/></a>
            </td>
            <td>
                <a href="<%= request.getContextPath() %>/task/rule?page=<%= request.getAttribute("currentPage") %>&task_id=<%= taskDto.getId() %>&action=update"><user:locale value="all.update"/></a>
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
        <%= Paginator.generateString((Integer)request.getAttribute("pages"), (Integer)request.getAttribute("currentPage"), request.getContextPath()+"/task/rule","filter_user",filterValue) %><br/><br/>
        <%
               }
        %>
        <font color="#FF0000">
        <%
            String error = (String)request.getAttribute("error");
            if(error != null && !error.equals("")){
                out.print(error);
            }
        %><br/>
        </font>
        <form action="<%= request.getContextPath() %>/task/rule" method="post">
            <table>
                <%
                    TaskDTO taskDto = (TaskDTO)request.getAttribute("taskToUpdate");
                    String id = "";
                    String title = "";
                    String text = "";
                    String hours = "";
                    String status = "";
                    String login = "";
                    String action = "add";
                    if(taskDto != null){
                        if(taskDto.getId() != null)
                            id = String.valueOf(taskDto.getId());
                        title = taskDto.getTitle();
                        text = taskDto.getText();
                        if(taskDto.getHoursToDo() != null)
                            hours = String.valueOf(taskDto.getHoursToDo());
                        status = taskDto.getStatus();
                        login = taskDto.getLogin();
                        if(error == null || request.getParameter("action_form").equals("update")){
                            action = "update";
                        }
                    }
                %>
                <tr>
                    <td>
                        <user:locale value="task.title"/>
                    </td>
                    <td>
                        <input type="hidden" name="task_id" value="<%= id %>"/>
                        <textarea name="task_title" style="width:200px" rows="3"><%= title %></textarea>
                    </td>
                </tr>
                <tr>
                    <td>
                        <user:locale value="task.text"/>
                    </td>
                    <td>
                        <textarea name="task_text" style="width:200px" rows="5"><%= text %></textarea>
                    </td>
                </tr>
                <tr>
                    <td>
                        <user:locale value="task.hours"/>
                    </td>
                    <td>
                        <input type="text" name="task_hours" value="<%= hours %>" style="width:100%"/>  
                    </td>
                </tr>
                <tr>
                    <td>
                        <user:locale value="task.status"/>
                    </td>
                    <td>
                        <select name="task_status" style="width:100%">
                            <option value="to do" <% if(status.equals("to do")) out.print("selected"); %>>to do</option>
                            <option value="in progress" <% if(status.equals("in progress")) out.print("selected"); %>>in progress</option>
                        </select>  
                    </td>
                </tr>
                <tr>
                    <td>
                        <user:locale value="task.user"/>
                    </td>
                    <td>
                        <select name="task_user" style="width:100%">
                            <%
                                List<UserDTO> listUser = (List<UserDTO>)request.getAttribute("userList");
                                Iterator<UserDTO> i = listUser.iterator();
                                while(i.hasNext()){
                                    UserDTO us = i.next();
                                    if(us.getLogin().equals(login)){
                                        %>
                                        <option value="<%= us.getLogin() %>" selected><%= us.getFirstName()+" "+us.getLastName() %></option>
                                        <%
                                    }
                                    else{
                                       %>
                                       <option value="<%= us.getLogin() %>"><%= us.getFirstName()+" "+us.getLastName() %></option>
                                       <%
                                    }
                                }
                            %>
                        </select>  
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
                        <input type="submit" name="task_submit" value="<user:locale value="all.update"/>"/>
                            <%
                                                           }
                                       else {
                            %>
                            <input type="submit" name="task_submit" value="<user:locale value="all.add"/>"/>
                            <%
                                                           }
                            %>
                    </td>
                </tr>
            </table>
        </form>
</div>
        <div style="float:right">
        <h4><user:locale value="all.filter"/></h4>
        <form action="<%= request.getContextPath() %>/task/rule" method="post">
        <table>
            <tr>
                <td>
                    <user:locale value="filter.user"/>
                </td>
                <td>
                    <select name="filter_user" style="width:150px">
                        <option value="0">...</option>
                        <%
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
