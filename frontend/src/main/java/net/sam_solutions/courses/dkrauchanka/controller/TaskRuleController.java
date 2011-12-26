package net.sam_solutions.courses.dkrauchanka.controller;

import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate.UserDAOImpl;
import net.sam_solutions.courses.dkrauchanka.domain.User;
import net.sam_solutions.courses.dkrauchanka.dto.TaskDTO;
import net.sam_solutions.courses.dkrauchanka.dto.UserDTO;
import net.sam_solutions.courses.dkrauchanka.framework.Action;
import net.sam_solutions.courses.dkrauchanka.service.TaskService;
import net.sam_solutions.courses.dkrauchanka.service.UserService;

public class TaskRuleController implements Action{
    private Integer number = 10;
    
    @Override
    public String perform(HttpServletRequest request) {
        TaskService taskService = new TaskService();
        UserService userService = new UserService();
        String action = request.getParameter("action");
        if(action != null && !action.equals("")){
            String id = request.getParameter("task_id");
            if(id != null && !id.equals("")){
                if(action.equals("delete")){
                    taskService.removeTask(Integer.valueOf(id));
                }
                else if(action.equals("update")){
                    TaskDTO taskToUpdate = taskService.getTask(Integer.valueOf(id));
                    request.setAttribute("taskToUpdate", taskToUpdate);
                }
            }
        }
        else if(request.getParameter("task_submit") != null) {
            ResourceBundle rs = ResourceBundle.getBundle("text", request.getLocale());
            String error = null;
            String id = request.getParameter("task_id");
            String title = request.getParameter("task_title");
            String text = request.getParameter("task_text");
            String hours = request.getParameter("task_hours");
            String status = request.getParameter("task_status");
            UserDAOImpl userDao = new UserDAOImpl();
            User user = userDao.getUser(request.getParameter("task_user"));
            Pattern p = Pattern.compile("\\d+");
            Integer idInt = null;
            if(!id.equals(""))
                idInt = Integer.valueOf(id);
            Integer hoursInt = null;
            if(!hours.equals("") && p.matcher(hours).matches())
                hoursInt = Integer.valueOf(hours);
            if(title == null || title.equals("")){
                error = rs.getString("error.titlenull");
                request.setAttribute("taskToUpdate", new TaskDTO(idInt,title,text,hoursInt,status,user));
                request.setAttribute("error", error);
            }
            else if(text == null || text.equals("")){
                error = rs.getString("error.textnull");
                request.setAttribute("taskToUpdate", new TaskDTO(idInt,title,text,hoursInt,status,user));
                request.setAttribute("error", error);
            }
            else if (!p.matcher(hours).matches()){
                error = rs.getString("error.notnumber");
                request.setAttribute("taskToUpdate", new TaskDTO(idInt,title,text,hoursInt,status,user));
                request.setAttribute("error", error);
            }
            else if(status == null || status.equals("")){
                error = rs.getString("error.statusnull");
                request.setAttribute("taskToUpdate", new TaskDTO(idInt,title,text,hoursInt,status,user));
                request.setAttribute("error", error);
            }
            else {
                if(!id.equals("")){
                    taskService.addNewTask(Integer.valueOf(id), title, text, Integer.valueOf(hours), status, request.getParameter("task_user"));
                }
                else{
                    taskService.addNewTask(null, title, text, Integer.valueOf(hours), status, request.getParameter("task_user"));
                }
            }
        }
        Integer page = 1;
        if(request.getParameter("page") != null && !request.getParameter("page").equals("")){
            page = Integer.valueOf(request.getParameter("page"));
        }
        List<TaskDTO> list = null;
        if(request.getParameter("filter_user") != null && !request.getParameter("filter_user").equals("0")){
            list = taskService.listTaskByUser(page, number, request.getParameter("filter_user"));
            request.setAttribute("pages",taskService.getPagesCount(number, "filter_user", request.getParameter("filter_user") ));
        }
        else{
            list = taskService.listTask(page, number);
            request.setAttribute("pages",taskService.getPagesCount(number, null, null ));
        }
        
        List<UserDTO> listUs = userService.listUsers(1, userService.getPagesCount(1));
        request.setAttribute("userList", listUs);
        request.setAttribute("taskList", list);
        request.setAttribute("currentPage", page);
        return "/WEB-INF/jsp/taskrule.jsp";
    }
    
}
