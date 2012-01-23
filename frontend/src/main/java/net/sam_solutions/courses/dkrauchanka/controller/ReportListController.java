package net.sam_solutions.courses.dkrauchanka.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.sam_solutions.courses.dkrauchanka.constants.Constants;
import net.sam_solutions.courses.dkrauchanka.dto.ReportDTO;
import net.sam_solutions.courses.dkrauchanka.dto.TaskDTO;
import net.sam_solutions.courses.dkrauchanka.dto.UserDTO;
import net.sam_solutions.courses.dkrauchanka.framework.Action;
import net.sam_solutions.courses.dkrauchanka.service.ReportService;
import net.sam_solutions.courses.dkrauchanka.service.TaskService;
import net.sam_solutions.courses.dkrauchanka.service.UserService;

public class ReportListController implements Action{
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
            list = reportService.listReportByUser(page, Constants.ROWS_ON_PAGE, request.getParameter("filter_user"));
        }
        else{
                    list = reportService.listReport(page, Constants.ROWS_ON_PAGE);
        }
        
        List<UserDTO> listUs = userService.listUsers(1, userService.getPagesCount(1));
        List<TaskDTO> listT = taskService.listTask(1, taskService.getPagesCount(1,null,null));
        request.setAttribute("taskList", listT);
        request.setAttribute("userList", listUs);
        request.setAttribute("reportList", list);
        request.setAttribute("currentPage", page);
        request.setAttribute("pages",reportService.getPagesCount(Constants.ROWS_ON_PAGE));
        return "/WEB-INF/jsp/reportlist.jsp";
    }
    
}
