package net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate;

import junit.framework.*;
import java.util.List;
import java.util.Set;
import java.util.Iterator;
import org.apache.log4j.Logger;

import net.sam_solutions.courses.dkrauchanka.domain.User;
import net.sam_solutions.courses.dkrauchanka.domain.Task;


public class UserDAOImplTest extends TestCase {
	public static final Logger logger = Logger.getLogger(UserDAOImplTest.class);	
	public UserDAOImplTest(String str){
		super(str);
	}
	
	public void testAddingUser(){
		UserDAOImpl userDao = new UserDAOImpl();
		User user = new User(null,"asd","sad","wqqwe","adsf","admin");
		userDao.addUser(user);
		Integer id = user.getId();
		User newUser = userDao.getUser(id);
		assertEquals(user,newUser);
		userDao.removeUser(user);
	}
	
	public void testRemovingUser(){
		UserDAOImpl userDao = new UserDAOImpl();
		User user = new User(null,"asd","sad","wqqwe","adsf","admin");
		userDao.addUser(user);
		Integer id = user.getId();
		userDao.removeUser(user);
		User newUser = userDao.getUser(id);
		assertNull(newUser);
	}
	
	public void testUpdatingUser(){
		UserDAOImpl userDao = new UserDAOImpl();
		User user = new User(null,"asd","sad","wqqwe","adsf","admin");
		userDao.addUser(user);
		Integer id = user.getId();
		user.setLogin("aaa");
		userDao.updateUser(user);
		User newUser = userDao.getUser(id);
		assertEquals(newUser.getLogin(),user.getLogin());
		userDao.removeUser(user);
	}
}
