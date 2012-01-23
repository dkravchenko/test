package net.sam_solutions.courses.dkrauchanka.service;

import net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate.RoleDAOImpl;
import net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate.TaskDAOImpl;
import net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate.UserDAOImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sam_solutions.courses.dkrauchanka.domain.Role;
import net.sam_solutions.courses.dkrauchanka.domain.Task;
import net.sam_solutions.courses.dkrauchanka.domain.User;
import net.sam_solutions.courses.dkrauchanka.dto.TaskDTO;
import org.apache.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestTaskService {
    public static final Logger log = Logger.getLogger(TestUserService.class);
    
    @Test
    public void testListing(){
        List<TaskDTO> list = new ArrayList<TaskDTO>();
        RoleDAOImpl roleDao = new RoleDAOImpl();
        roleDao.addRole(new Role("user","afsdf"));
        User user = new User("qwe","asd","as","das",new Role("user","afsdf"));
        UserDAOImpl userDao = new UserDAOImpl();
        userDao.addUser(user);
        TaskService taskService = new TaskService();
        TaskDAOImpl taskDao = new TaskDAOImpl();
        Task task = new Task(null, "asd","sd",12,"to do",new Date(),user);
        taskDao.addTask(task);
        list.add(new TaskDTO(task));
        task = new Task(null,"xzc","dsa",12,"in progress",new Date(), user);
        taskDao.addTask(task);
        list.add(new TaskDTO(task));
        assertEquals(list, taskService.listTask(1, 2));
        roleDao.removeRole(new Role("user","afsdf"));
    }
    
}
