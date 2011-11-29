package net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate;

import java.util.Date;
import junit.framework.*;
import org.apache.log4j.Logger;

import net.sam_solutions.courses.dkrauchanka.domain.Task;
import net.sam_solutions.courses.dkrauchanka.domain.User;


public class TaskDAOImplTest extends TestCase {
	public static final Logger logger = Logger.getLogger(UserDAOImplTest.class);	
	public TaskDAOImplTest(String str){
		super(str);
	}
	
	public void testAddingTask(){
		TaskDAOImpl taskDao = new TaskDAOImpl();
                UserDAOImpl userDao = new UserDAOImpl();
		User user = new User("7","zdxcv","asdf","asd","user");
                userDao.addUser(user);
		Task task = new Task(null,"asd","sad","/0","to do",new Date(),1,user);
		taskDao.addTask(task);
		Integer id = task.getId();
                logger.fatal(id);
		Task newTask = taskDao.getTask(id);
		assertEquals(task.getUser(),newTask.getUser());
		taskDao.removeTask(task);
                userDao.removeUser(user);
	}
	
	public void testRemovingTask(){
		TaskDAOImpl taskDao = new TaskDAOImpl();
		UserDAOImpl userDao = new UserDAOImpl();
		User user = new User("8","zdxcv","asdf","asd","user");
                userDao.addUser(user);
		Task task = new Task(null,"asd","sad","/0","to do",new Date(),1,user);
		taskDao.addTask(task);
		Integer id = task.getId();
		taskDao.removeTask(task);
               userDao.removeUser(user);
		Task newTask = taskDao.getTask(id);
		assertNull(newTask);
	}
	
	public void testUpdatingTask(){
		TaskDAOImpl taskDao = new TaskDAOImpl();
		UserDAOImpl userDao = new UserDAOImpl();
		User user = new User("9","zdxcv","asdf","asd","user");
                userDao.addUser(user);
		Task task = new Task(null,"asd","sad","/0","to do",new Date(),1,user);
		taskDao.addTask(task);
		Integer id = task.getId();
		task.setTitle("aaa");
		taskDao.addTask(task);
		Task newTask = taskDao.getTask(id);
		assertEquals(newTask.getTitle(),task.getTitle());
		taskDao.removeTask(task);
                userDao.removeUser(user);
	}
}