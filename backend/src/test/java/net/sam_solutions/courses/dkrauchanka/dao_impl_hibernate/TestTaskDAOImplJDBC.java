package net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate;

import net.sam_solutions.courses.dkrauchanka.utils.HSQLDBUtil;
import org.junit.BeforeClass;
import net.sam_solutions.courses.dkrauchanka.domain.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import net.sam_solutions.courses.dkrauchanka.utils.Password;
import org.apache.log4j.Logger;
import org.junit.Test;
import net.sam_solutions.courses.dkrauchanka.dao_impl_jdbc.RoleDAOImpl;
import net.sam_solutions.courses.dkrauchanka.dao_impl_jdbc.TaskDAOImpl;
import net.sam_solutions.courses.dkrauchanka.domain.User;
import net.sam_solutions.courses.dkrauchanka.domain.Role;
import net.sam_solutions.courses.dkrauchanka.domain.Report;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestTaskDAOImplJDBC {
    public static final Logger log = Logger.getLogger(TestTaskDAOImplJDBC.class);
    
    @BeforeClass
    public static void before(){
        HSQLDBUtil.getInstance().prepare();
    }
    
    @Test
    public void testAdding(){
        Role role = new Role("admin","asdas, dfasd");
        User user = new User("demon13.by",Password.hashPassword(Password.hashPassword("Scorpion")+Password.SALT),"Denis","Kravchenko",role);
        Report report = new Report(null,new Date(),56,12,user);
        List<Report> reports = new ArrayList<Report>();
        reports.add(report);
        Task task = new Task(null,"asdasdasd","asdasd",56,"asas",new Date(),user);
        task.setReports(reports);
        TaskDAOImpl taskDao = new TaskDAOImpl();
        taskDao.addTask(task);
        Integer id = task.getId();
        Task newTask = taskDao.getTask(id);
        assertEquals(task,newTask);
        RoleDAOImpl roleDao = new RoleDAOImpl();
        roleDao.removeRole(role);
    }
    
    @Test
    public void removingUser(){
        Role role = new Role("admin","asdas, dfasd");
        User user = new User("demon13.by",Password.hashPassword(Password.hashPassword("Scorpion")+Password.SALT),"Denis","Kravchenko",role);
        Report report = new Report(null,new Date(),56,12,user);
        List<Report> reports = new ArrayList<Report>();
        reports.add(report);
        Task task = new Task(null,"asdasdasd","asdasd",56,"asas",new Date(),user);
        TaskDAOImpl taskDao = new TaskDAOImpl();
        taskDao.addTask(task);
        Integer id = task.getId();
        taskDao.removeTask(task);
        Task newTask = taskDao.getTask(id);
        assertNull(newTask);
        RoleDAOImpl roleDao = new RoleDAOImpl();
        roleDao.removeRole(role);
    }
    
    @Test
    public void updatingUser(){
        Role role = new Role("admin","asdas, dfasd");
        User user = new User("demon13.by",Password.hashPassword(Password.hashPassword("Scorpion")+Password.SALT),"Denis","Kravchenko",role);
        Report report = new Report(null,new Date(),56,12,user);
        List<Report> reports = new ArrayList<Report>();
        reports.add(report);
        Task task = new Task(null,"asdasdasd","asdasd",56,"asas",new Date(),user);
        task.setReports(reports);
        TaskDAOImpl taskDao = new TaskDAOImpl();
        taskDao.addTask(task);
        log.fatal(task);
        Integer id = task.getId();
        task.setText("fjghkl");
        taskDao.addTask(task);
        log.fatal(task);
        Task newTask = taskDao.getTask(id);
        log.fatal(newTask);
        assertEquals(task,newTask);
        RoleDAOImpl roleDao = new RoleDAOImpl();
        roleDao.removeRole(role);
    }
}
