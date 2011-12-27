<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div style="float:left; width:200px">
    <a href="<%= request.getContextPath() %>/task/list"><user:locale value="menu.tasklist"/></a><br/>
    <a href="<%= request.getContextPath() %>/task/self"><user:locale value="menu.taskself"/></a><br/>
    <a href="<%= request.getContextPath() %>/report/add"><user:locale value="menu.reportadd"/></a><br/>
    <a href="<%= request.getContextPath() %>/report/self"><user:locale value="menu.reportself"/></a><br/>
    <a href="<%= request.getContextPath() %>/logout"><user:locale value="menu.logout"/></a><br/>
</div>
