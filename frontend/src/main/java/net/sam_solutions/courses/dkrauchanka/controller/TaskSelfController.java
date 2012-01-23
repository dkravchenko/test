package net.sam_solutions.courses.dkrauchanka.controller;

import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import net.sam_solutions.courses.dkrauchanka.constants.Constants;
import net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate.UserDAOImpl;
import net.sam_solutions.courses.dkrauchanka.domain.User;
import net.sam_solutions.courses.dkrauchanka.dto.TaskDTO;
import net.sam_solutions.courses.dkrauchanka.framework.Action;
import net.sam_solutions.courses.dkrauchanka.service.TaskService;

public class TaskSelfController implements Action{
    @Override
    public String perform(HttpServletRequest request) {
        ResourceBundle rs = ResourceBundle.getBundle("text", request.getLocale());
        TaskService taskService = new TaskService();
        String error = null;
        String add_error = null;
        String action = request.getParameter("action");
        if(action != null && !action.equals("")){
            String id = request.getParameter("task_id");
            if(id != null && !id.equals("")){
                if(action.equals("close")){
                    taskService.closeTask(Integer.valueOf(id));
                }
                else if(action.equals("change_status")){
                    TaskDTO taskToUpdate = taskService.getTask(Integer.valueOf(id));
                    request.setAttribute("taskToUpdate", taskToUpdate);
                }
            }
        }
        else if(request.getParameter("submit") != null) {
            String id = request.getParameter("task_id");
            String status = request.getParameter("status");
            if(status == null || status.equals("")){
                error = rs.getString("error.statusnull");
                TaskDTO task = taskService.getTask(Integer.valueOf(id));
                task.setStatus("");
                request.setAttribute("taskToUpdate", task);
            }
            else{
               taskService.changeTaskStatus(Integer.valueOf(id), status);
            }
        }
        else if(request.getParameter("add_task_submit") != null) {

            String title = request.getParameter("add_task_title");
            String text = request.getParameter("add_task_text");
            String hours = request.getParameter("add_task_hours");
            String status = request.getParameter("add_task_status");
            UserDAOImpl userDao = new UserDAOImpl();
            User user = userDao.getUser((String)request.getSession().getAttribute("login"));
            Pattern p = Pattern.compile("\\d+");
            Integer idInt = null;
            Integer hoursInt = null;
            if(!hours.equals("") && p.matcher(hours).matches())
                hoursInt = Integer.valueOf(hours);
            if(title == null || title.equals("")){
                add_error = rs.getString("error.titlenull");
                request.setAttribute("taskToAdd", new TaskDTO(idInt,title,text,hoursInt,status,user.getFirstName()+" "+user.getLastName(),user.getLogin()));
            }
            else if(text == null || text.equals("")){
                add_error = rs.getString("error.textnull");
                request.setAttribute("taskToAdd", new TaskDTO(idInt,title,text,hoursInt,status,user.getFirstName()+" "+user.getLastName(),user.getLogin()));
            }
            else if (!p.matcher(hours).matches()){
                add_error = rs.getString("error.notnumber");
                request.setAttribute("taskToAdd", new TaskDTO(idInt,title,text,hoursInt,status,user.getFirstName()+" "+user.getLastName(),user.getLogin()));
            }
            else if(status == null || status.equals("")){
                add_error = rs.getString("error.statusnull");
                request.setAttribute("taskToAdd", new TaskDTO(idInt,title,text,hoursInt,status,user.getFirstName()+" "+user.getLastName(),user.getLogin()));
            }
            else {
                taskService.addNewTask(null, title, text, Integer.valueOf(hours), status, user.getLogin());
            }
        }
        Integer page = 1;
        if(request.getParameter("page") != null && !request.getParameter("page").equals("")){
            page = Integer.valueOf(request.getParameter("page"));
        }
        List<TaskDTO> list = taskService.listTaskByUser(page, Constants.ROWS_ON_PAGE, (String)request.getSession().getAttribute("login"));
        request.setAttribute("pages",taskService.getPagesCount(Constants.ROWS_ON_PAGE, "filter_user", (String)request.getSession().getAttribute("login") ));
        request.setAttribute("taskList", list);
        request.setAttribute("error", error);
        request.setAttribute("add_error", add_error);
        request.setAttribute("currentPage", page);
        return "/WEB-INF/jsp/taskself.jsp";
    }
    
}
