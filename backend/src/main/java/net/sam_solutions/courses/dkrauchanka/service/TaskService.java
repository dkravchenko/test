package net.sam_solutions.courses.dkrauchanka.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate.ReportDAOImpl;
import net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate.TaskDAOImpl;
import net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate.UserDAOImpl;
import net.sam_solutions.courses.dkrauchanka.domain.Report;
import net.sam_solutions.courses.dkrauchanka.domain.Task;
import net.sam_solutions.courses.dkrauchanka.domain.User;
import net.sam_solutions.courses.dkrauchanka.dto.DTOConverter;
import net.sam_solutions.courses.dkrauchanka.dto.TaskDTO;

public class TaskService {
    private TaskDAOImpl taskDao;
    
    public TaskService(){
        this.taskDao = new TaskDAOImpl();
    }
    
    public void createTask(String title,String text, Integer hoursTodo, String login){
        UserDAOImpl userDao = new UserDAOImpl();
        User user = userDao.getUser(login);
        Task task = new Task(null,title,text,hoursTodo,"todo",null,user);
        taskDao.addTask(task);
    }
    
    public List<TaskDTO> listTask(int page, int count){
        List<Task> list = taskDao.listTask(page, count);
        return DTOConverter.taskToTaskDTOList(list);
    }
    
    public List<TaskDTO> listTaskByUser(int page, int count, String login){
        List<Task> list = taskDao.listTaskByUser(page, count, login);
        return DTOConverter.taskToTaskDTOList(list);
    }
    
    public Integer getPagesCount(int onPage, String filterName, String filterValue){
        Long count = null;
        if(filterName != null && filterName.equals("filter_user")){
            count = taskDao.countTaskByUser(filterValue);
        }
        else{
            count = taskDao.countTask();
        }
        return (int)Math.ceil((double)count/onPage);
    }
    
    public List<TaskDTO> showUserTasks(String login, int page, int count){
        UserDAOImpl userDao = new UserDAOImpl();
        User user = userDao.getUser(login);
        List<Task> list = taskDao.listTaskByUser(user,page,count);
        List<TaskDTO> listDto = DTOConverter.taskToTaskDTOList(list);
        return listDto;
    }
    
    public List<TaskDTO> showUserUnclosedTasks(String login, int page, int count){
        List<Task> list = taskDao.listUnclosedTaskByUser(page,count,login);
        List<TaskDTO> listDto = DTOConverter.taskToTaskDTOList(list);
        return listDto;
    }
    
    public void closeTask(Integer id){
        Task task = taskDao.getTask(id);
        ReportDAOImpl reportDao = new ReportDAOImpl();
        Report report = new Report(null,new Date(),0,task.getHoursToDo(),task.getUser());
        List<Task> list = new ArrayList<Task>();
        list.add(task);
        report.setTasks(list);
        reportDao.addReport(report);
        task.setHoursToDo(0);
        task.setStatus("closed");
        taskDao.addTask(task);
    }
    
    public void changeTaskStatus(Integer id, String status){
        Task task = taskDao.getTask(id);
        task.setStatus(status);
        taskDao.addTask(task);
    }
    
    public void changeTaskInfo(Integer id, String title, String text, Integer hoursToDo,String login){
        Task task = taskDao.getTask(id);
        task.setTitle(title);
        task.setText(text);
        task.setHoursToDo(hoursToDo);
        UserDAOImpl userDao = new UserDAOImpl();
        User user = userDao.getUser(login);
        task.setUser(user);
        taskDao.addTask(task);
    }
    
    public void removeTask(Integer id){
        Task task = taskDao.getTask(id);
        taskDao.removeTask(task);
    }
    
    public TaskDTO getTask(Integer id){
        Task task = taskDao.getTask(id);
        return new TaskDTO(task);
    }
    
    public void addNewTask(Integer id, String title, String text, Integer hours, String status, String login){
        UserDAOImpl userDao = new UserDAOImpl();
        User user = userDao.getUser(login);
        Task task = new Task(id,title,text,hours,status,null,user);
        taskDao.addTask(task);
    }
}
