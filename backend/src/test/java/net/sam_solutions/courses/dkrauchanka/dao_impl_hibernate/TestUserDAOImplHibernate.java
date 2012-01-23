package net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import net.sam_solutions.courses.dkrauchanka.utils.HSQLDBUtil;
import org.junit.BeforeClass;
import net.sam_solutions.courses.dkrauchanka.utils.Password;
import org.apache.log4j.Logger;
import org.junit.Test;
import net.sam_solutions.courses.dkrauchanka.domain.User;
import net.sam_solutions.courses.dkrauchanka.domain.Role;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestUserDAOImplHibernate {
    public static final Logger log = Logger.getLogger(TestUserDAOImplHibernate.class);
    
    @BeforeClass
    public static void before(){
        BeanFactory factory = new XmlBeanFactory(new ClassPathResource("spring.xml"));
        HSQLDBUtil util = (HSQLDBUtil) factory.getBean("HSQLDBUtil");
        util.prepare();
    }
    
    @Test
    public void testAdding(){
        Role role = new Role("admin","asdas, dfasd");
        RoleDAOImpl roleDao = new RoleDAOImpl();
        roleDao.addRole(role);
        UserDAOImpl userDao = new UserDAOImpl();
        User user = new User("demon13.by",Password.hashPassword(Password.hashPassword("Scorpion")+Password.SALT),"Denis","Kravchenko",role);
        userDao.addUser(user);
        String login = user.getLogin();
        User newUser = userDao.getUser(login);
        assertEquals(user,newUser);
        userDao.removeUser(user);
        roleDao.removeRole(role);
    }
    
    @Test
    public void removingUser(){
        Role role = new Role("admin","asdas, dfasd");
        RoleDAOImpl roleDao = new RoleDAOImpl();
        roleDao.addRole(role);
        UserDAOImpl userDao = new UserDAOImpl();
        User user = new User("demon13.by",Password.hashPassword(Password.hashPassword("Scorpion")+Password.SALT),"Denis","Kravchenko",role);
        userDao.addUser(user);
        String login = user.getLogin();
        userDao.removeUser(user);
        User newUser = userDao.getUser(login);
        assertNull(newUser);
        roleDao.removeRole(role);
    }
    
    @Test
    public void updatingUser(){
        Role role = new Role("admin","asdas, dfasd");
        RoleDAOImpl roleDao = new RoleDAOImpl();
        roleDao.addRole(role);
        UserDAOImpl userDao = new UserDAOImpl();
        User user = new User("demon13.by",Password.hashPassword(Password.hashPassword("Scorpion")+Password.SALT),"Denis","Kravchenko",role);
        userDao.addUser(user);
        String login = user.getLogin();
        user.setFirstName("Den");
        userDao.addUser(user);
        User newUser = userDao.getUser(login);
        assertEquals(user,newUser);
        userDao.removeUser(user);
        roleDao.removeRole(role);
    }
}
