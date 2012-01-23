package net.sam_solutions.courses.dkrauchanka.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.sam_solutions.courses.dkrauchanka.constants.Constants;
import net.sam_solutions.courses.dkrauchanka.dto.TaskDTO;
import net.sam_solutions.courses.dkrauchanka.dto.UserDTO;
import net.sam_solutions.courses.dkrauchanka.framework.Action;
import net.sam_solutions.courses.dkrauchanka.service.TaskService;
import net.sam_solutions.courses.dkrauchanka.service.UserService;

public class TaskListController implements Action{
    @Override
    public String perform(HttpServletRequest request) {
        TaskService taskService = new TaskService();
        UserService userService = new UserService();
        Integer page = 1;
        if(request.getParameter("page") != null && !request.getParameter("page").equals("")){
            page = Integer.valueOf(request.getParameter("page"));
        }
        List<TaskDTO> list = null;
        if(request.getParameter("filter_user") != null && !request.getParameter("filter_user").equals("0")){
            list = taskService.listTaskByUser(page, Constants.ROWS_ON_PAGE, request.getParameter("filter_user"));
            request.setAttribute("pages",taskService.getPagesCount(Constants.ROWS_ON_PAGE, "filter_user", request.getParameter("filter_user") ));
        }
        else{
            list = taskService.listTask(page, Constants.ROWS_ON_PAGE);
            request.setAttribute("pages",taskService.getPagesCount(Constants.ROWS_ON_PAGE, null, null ));
        }
        
        List<UserDTO> listUs = userService.listUsers(1, userService.getPagesCount(1));
        request.setAttribute("userList", listUs);
        request.setAttribute("taskList", list);
        request.setAttribute("currentPage", page);
        return "/WEB-INF/jsp/tasklist.jsp";
    }
    
}
