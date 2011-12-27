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
            <td>
                <user:locale value="task.close"/>
            </td>
            <td>
                <user:locale value="task.changestatus"/>
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
                <%= taskDto.getUserDto().getFirstName()+" "+taskDto.getUserDto().getLastName() %>
            </td>
            <%
                if(!taskDto.getStatus().equals("closed"))
                {
            %>
            <td>
                <a href="<%= request.getContextPath() %>/task/self?page=<%= request.getAttribute("currentPage") %>&task_id=<%= taskDto.getId() %>&action=close"><user:locale value="task.close"/></a>
            </td>
            <td>
                <a href="<%= request.getContextPath() %>/task/self?page=<%= request.getAttribute("currentPage") %>&task_id=<%= taskDto.getId() %>&action=change_status"><user:locale value="task.changestatus"/></a>
            </td>
            <%
                       } else{
                %>
                <td></td><td></td>
        </tr>
        <%
                      }}
        %>
        </table>
        <%= Paginator.generateString((Integer)request.getAttribute("pages"), (Integer)request.getAttribute("currentPage"), request.getContextPath()+"/task/list",null,null) %><br/><br/>
        <%
               }
        %>
        <font color="#FF0000">
        <%
            String add_error = (String)request.getAttribute("add_error");
            if(add_error != null && !add_error.equals("")){
                out.print(add_error);
            }
        %><br/>
        </font>
        <form action="<%= request.getContextPath() %>/task/self" method="post">
            <table>
                <%
                    TaskDTO addTaskDto = (TaskDTO)request.getAttribute("taskToAdd");
                    String add_title = "";
                    String add_text = "";
                    String add_hours = "";
                    String add_status = "";
                    String action = "add";
                    if(addTaskDto != null){
                        add_title = addTaskDto.getTitle();
                        add_text = addTaskDto.getText();
                        if(addTaskDto.getHoursToDo() != null)
                            add_hours = String.valueOf(addTaskDto.getHoursToDo());
                        add_status = addTaskDto.getStatus();
                    }
                %>
                <tr>
                    <td>
                        <user:locale value="task.title"/>
                    </td>
                    <td>
                        <textarea name="add_task_title" style="width:200px" rows="3"><%= add_title %></textarea>
                    </td>
                </tr>
                <tr>
                    <td>
                        <user:locale value="task.text"/>
                    </td>
                    <td>
                        <textarea name="add_task_text" style="width:200px" rows="5"><%= add_text %></textarea>
                    </td>
                </tr>
                <tr>
                    <td>
                        <user:locale value="task.hours"/>
                    </td>
                    <td>
                        <input type="text" name="add_task_hours" value="<%= add_hours %>" style="width:100%"/>  
                    </td>
                </tr>
                <tr>
                    <td>
                        <user:locale value="task.status"/>
                    </td>
                    <td>
                        <select name="add_task_status" style="width:100%">
                            <option value="to do" <% if(add_status.equals("to do")) out.print("selected"); %>>to do</option>
                            <option value="in progress" <% if(add_status.equals("in progress")) out.print("selected"); %>>in progress</option>
                        </select>  
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="hidden" name="add_form" value="<%= action %>"/>
                    </td>
                    <td>
                            <input type="submit" name="add_task_submit" value="<user:locale value="all.add"/>"/>
                    </td>
                </tr>
            </table>
        </form>
        </div>
        <div style="width:300px; float:right">
            <h4><user:locale value="task.changestatus"/></h4>
            <%
                String id = "";
                String title = "";
                String text = "";
                String hours = "";
                String status = "";
                String us = "";
                TaskDTO taskDto = (TaskDTO)request.getAttribute("taskToUpdate");
                if(taskDto != null){
                    id = String.valueOf(taskDto.getId());
                    title = taskDto.getTitle();
                    text = taskDto.getText();
                    hours = String.valueOf(taskDto.getHoursToDo());
                    status = taskDto.getStatus();
                    us = taskDto.getUserDto().getFirstName()+taskDto.getUserDto().getLastName();        
                }
            %>
            <font color="#FF0000">
            <%
                String error = (String)request.getAttribute("error");
                if(error != null && !error.equals(""))
                    out.print(error);
            %>
            </font>
            <form action="<%= request.getContextPath() %>/task/self" method="post">
            <table>
                
                <tr>
                    <td>
                        <user:locale value="task.title"/>
                    </td>
                    <td>
                        <%= title %>
                    </td>
                </tr>
                <tr>
                    <td>
                        <user:locale value="task.text"/>
                    </td>
                    <td>
                        <%= text %>
                    </td>
                </tr>
                <tr>
                    <td>
                        <user:locale value="task.hours"/>
                    </td>
                    <td>
                        <%= hours %>
                    </td>
                </tr>
                <tr>
                    <td>
                        <user:locale value="task.status"/>
                    </td>
                    <td>
                        <%
                            if(status != null && !status.equals("")){
                                
                        %>
                        <select name="status">
                            <option value="to do" <% if(status.equals("to do")) out.print("selected"); %>>to do</option>
                            <option value="in progress" <% if(status.equals("in progress")) out.print("selected"); %>>in progress</option>
                        </select>
                            <%}
                %>
                    </td>
                </tr>
                <tr>
                    <td>
                        <user:locale value="task.user"/>
                    </td>
                    <td>
                        <%= us %>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="hidden" name="task_id" value="<%= id %>"/>
                    </td>
                    <td>
                        <%
                            if(status != null && !status.equals("")){
                                
                        %>
                        <input type="submit" name="submit" value="<user:locale value="task.changestatus"/>"/>
                        <%
                                               }
                        %>
                    </td>
                </tr>
                
            </table>
                    </form>
        </div>
        <%@include file="footer.jsp" %>