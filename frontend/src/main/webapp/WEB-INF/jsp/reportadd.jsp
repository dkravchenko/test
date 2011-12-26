<%@page import="net.sam_solutions.courses.dkrauchanka.dto.ReportDTO" %>
<%@page import="net.sam_solutions.courses.dkrauchanka.dto.UserDTO" %>
<%@page import="net.sam_solutions.courses.dkrauchanka.dto.TaskDTO" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Iterator" %>
<%@page import="net.sam_solutions.courses.dkrauchanka.tags.Paginator" %>
<%@include file="header.jsp" %>
<div style="float:left; margin:10px"> 
    <h1><user:locale value="reportadd.header"/></h1>
    <form action ="<%= request.getContextPath() %>/report/add" method="post">
        <table>
            <tr>
                <td>
                    <user:locale value="reportadd.workinghours"/>
                </td>
                <td>
                    <user:locale value="reportadd.donehours"/>
                </td>
                <td>
                    <user:locale value="reportadd.tasks"/>
                </td>
            </tr>
            <tr>
                <td>
                    <font color="#FF0000">
                    <%
                        String errorWorkingHours = (String)request.getAttribute("errorWorkingHours");
                        if(errorWorkingHours != null && !errorWorkingHours.equals("")){
                            out.print(errorWorkingHours);
                        }
                    %>
                    </font><br/>
                    <input type="text" name="working_hours"/>
                </td>
                <td>
                    <font color="#FF0000">
                    <%
                        String errorDoneHours = (String)request.getAttribute("errorDoneHours");
                        if(errorDoneHours != null && !errorDoneHours.equals("")){
                            out.print(errorDoneHours);
                        }
                    %>
                    </font><br/>
                    <div id="hours_done"></div> 
                </td>
                <td>
                    <select id="select" name="tasks">
                        <%
                            List<TaskDTO> list = (List<TaskDTO>)request.getAttribute("taskList");
                            if(list != null) {
                            Iterator<TaskDTO> iter = list.iterator();
                            while(iter.hasNext()){
                                TaskDTO taskDto = iter.next();
                                %>
                                <option value="<%= taskDto.getId() %>"><%= taskDto.getTitle() %></option>
                                <%
                            }}
                        %>
                    </select><input type="button" onclick="addTask();" value="<user:locale value="addreport.addtask"/>"/><br/>
                    <div id="tasks"></div>
                    
                </td>
            </tr>
            <tr>
                <td>
                    
                </td>
                <td>
                    <input type="submit" name="add_report" value="<user:locale value="addreport.add"/>" style="float:left"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<%@include file="footer.jsp" %>