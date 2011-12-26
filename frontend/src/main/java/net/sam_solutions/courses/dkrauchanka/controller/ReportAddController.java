package net.sam_solutions.courses.dkrauchanka.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import net.sam_solutions.courses.dkrauchanka.dto.TaskDTO;
import net.sam_solutions.courses.dkrauchanka.framework.Action;
import net.sam_solutions.courses.dkrauchanka.service.ReportService;
import net.sam_solutions.courses.dkrauchanka.service.TaskService;

public class ReportAddController implements Action{

    @Override
    public String perform(HttpServletRequest request) {
        ResourceBundle rs = ResourceBundle.getBundle("text", request.getLocale());
        TaskService taskService = new TaskService();
        ReportService reportService = new ReportService();
        List<String> hidden = new ArrayList<String>();
        List<String> hours = new ArrayList<String>();
        String hoursStr = "hours";
        String hiddenStr = "hidden";
        Integer count = 1;
        Integer errors = 0;
        String errorWorkingHours = null;
        String errorDoneHours = null;
        if(request.getParameter("add_report") != null){
            while(request.getParameter(hiddenStr + String.valueOf(count)) != null){
                String value = request.getParameter(hoursStr + String.valueOf(count));
                if(value == null || value.equals("")){
                    errors++;
                    break;
                }
                else{
                    hours.add(value);
                    hidden.add(request.getParameter(hiddenStr + String.valueOf(count)));
                }
                count ++;
            }
            if(errors != 0){
                errorDoneHours = rs.getString("error.nulldonehours");
            }
            else{
                String workingHours = request.getParameter("working_hours");
                if(workingHours != null && !workingHours.equals("")){
                    reportService.addReport(hidden, hours, workingHours, (String)request.getSession().getAttribute("login"));
                }
                else{
                    errorWorkingHours = rs.getString("error.nullworkinghours");
                }
            }
        }
        List<TaskDTO> list = taskService.showUserUnclosedTasks((String)request.getSession().getAttribute("login"),1, taskService.getPagesCount(1, null, null));
        request.setAttribute("taskList", list);
        request.setAttribute("errorWorkingHours", errorWorkingHours);
        request.setAttribute("errorDoneHours", errorDoneHours);
        return "/WEB-INF/jsp/reportadd.jsp";
    }
}
