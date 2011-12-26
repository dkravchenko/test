<%@page import="net.sam_solutions.courses.dkrauchanka.dto.ReportDTO" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Iterator" %>
<%@page import="net.sam_solutions.courses.dkrauchanka.tags.Paginator" %>
<%@include file="header.jsp" %>
<div style="float:left; margin:10px"> 
    <h1><user:locale value="reportself.header"/></h1>
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
                <user:locale value="reportself.dateofreport"/>
            </td>
            <td>
                <user:locale value="reportself.workinghours"/>
            </td>
            <td>
                <user:locale value="reportself.donehours"/>
            </td>
            <td>
                <user:locale value="reportself.tasks"/>
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
                <%= reportDto.getTasks() %>
            </td>
        </tr>
        <%
               }
        %>
    </table>
    <%= Paginator.generateString((Integer)request.getAttribute("pages"), (Integer)request.getAttribute("currentPage"), request.getContextPath()+"/report/self",null,null) %><br/><br/>
        <%
               }
        %>
        </div>
<%@include file="footer.jsp" %>
