package net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate;

import junit.framework.*;
import java.util.List;
import net.sam_solutions.courses.dkrauchanka.domain.User;


public class UserDAOImplTest extends TestCase {
	public UserDAOImplTest(String str){
		super(str);
	}

	public void testListUserNull(){
		UserDAOImpl userDao = new UserDAOImpl();
		List<User> list = userDao.listUser();
		assertTrue(list != null);
	}
	
}
