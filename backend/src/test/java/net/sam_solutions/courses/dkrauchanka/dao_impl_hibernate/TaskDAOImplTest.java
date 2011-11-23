package net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate;

import junit.framework.*;
import java.util.List;
import java.util.Set;
import java.util.Iterator;
import org.apache.log4j.Logger;

import net.sam_solutions.courses.dkrauchanka.domain.Task;


public class TaskDAOImplTest extends TestCase {
	public static final Logger logger = Logger.getLogger(UserDAOImplTest.class);	
	public TaskDAOImplTest(String str){
		super(str);
	}
	
	public void testAddingTask(){
		TaskDAOImpl taskDao = new TaskDAOImpl();
		Task task = new Task(null,"asd","sad","/0");
		taskDao.addTask(task);
		Integer id = task.getId();
		Task newTask = taskDao.getTask(id);
		assertEquals(task,newTask);
		taskDao.removeTask(task);
	}
	
	public void testRemovingTask(){
		TaskDAOImpl taskDao = new TaskDAOImpl();
		Task task = new Task(null,"asd","sad","/0");
		taskDao.addTask(task);
		Integer id = task.getId();
		taskDao.removeTask(task);
		Task newTask = taskDao.getTask(id);
		assertNull(newTask);
	}
	
	public void testUpdatingTASK(){
		TaskDAOImpl taskDao = new TaskDAOImpl();
		Task task = new Task(null,"asd","sad","/0");
		taskDao.addTask(task);
		Integer id = task.getId();
		task.setTitle("aaa");
		taskDao.updateTask(task);
		Task newTask = taskDao.getTask(id);
		assertEquals(newTask.getTitle(),task.getTitle());
		taskDao.removeTask(task);
	}
}