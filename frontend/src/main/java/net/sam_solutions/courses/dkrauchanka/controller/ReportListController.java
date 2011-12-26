package net.sam_solutions.courses.dkrauchanka.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import net.sam_solutions.courses.dkrauchanka.dto.ReportDTO;
import net.sam_solutions.courses.dkrauchanka.dto.TaskDTO;
import net.sam_solutions.courses.dkrauchanka.dto.UserDTO;
import net.sam_solutions.courses.dkrauchanka.framework.Action;
import net.sam_solutions.courses.dkrauchanka.service.ReportService;
import net.sam_solutions.courses.dkrauchanka.service.TaskService;
import net.sam_solutions.courses.dkrauchanka.service.UserService;

public class ReportListController implements Action{
    private Integer number = 10;

    @Override
    public String perform(HttpServletRequest request) {
        ReportService reportService = new ReportService();
        TaskService taskService = new TaskService();
        UserService userService = new UserService();
        Integer page = 1;
        if(request.getParameter("page") != null && !request.getParameter("page").equals("")){
            page = Integer.valueOf(request.getParameter("page"));
        }
        List<ReportDTO> list = null;
        if(request.getParameter("filter_user") != null && !request.getParameter("filter_user").equals("0")){
            list = reportService.listReportByUser(page, number, request.getParameter("filter_user"));
        }
        else{
            if(request.getParameter("filter_task") != null && !request.getParameter("filter_task").equals("0")){
                list = reportService.listReportByTask(page, number, Integer.valueOf(request.getParameter("filter_task")));
            }
            else{
                if(request.getParameter("filter_date") != null && !request.getParameter("filter_date").equals("")){
                    Pattern p = Pattern.compile("\\d{4}\\-\\d{2}\\-\\d{2}");
                    Matcher m = p.matcher(request.getParameter("filter_date"));
                    if(m.matches()){
                        list = reportService.listReportByDate(page, number, request.getParameter("filter_date"));
                    }
                    else{
                        request.setAttribute("error", "\"XXXX-XX-XX\"");
                    }        
                }
                else {
                    list = reportService.listReport(page, number);
                }
            }
        }
        
        List<UserDTO> listUs = userService.listUsers(1, userService.getPagesCount(1));
        List<TaskDTO> listT = taskService.listTask(1, taskService.getPagesCount(1,null,null));
        request.setAttribute("taskList", listT);
        request.setAttribute("userList", listUs);
        request.setAttribute("reportList", list);
        request.setAttribute("currentPage", page);
        request.setAttribute("pages",reportService.getPagesCount(number));
        return "/WEB-INF/jsp/reportlist.jsp";
    }
    
}
