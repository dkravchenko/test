package net.sam_solutions.courses.dkrauchanka.service;

import net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate.RoleDAOImpl;
import net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate.UserDAOImpl;
import java.util.ArrayList;
import java.util.List;
import net.sam_solutions.courses.dkrauchanka.domain.Role;
import net.sam_solutions.courses.dkrauchanka.dto.UserDTO;
import org.apache.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class TestUserService {
    public static final Logger log = Logger.getLogger(TestUserService.class);
    
    @Test
    public void testAddingUser(){
        RoleDAOImpl roleDao = new RoleDAOImpl();
        roleDao.addRole(new Role("user","afsdf"));
        UserDTO userDto = new UserDTO("demon13@np.by","asd","asd");
        UserService userService = new UserService();
        userService.addNewUser("demon13@np.by","asd","asd");
        UserDAOImpl userDao = new UserDAOImpl();
        UserDTO newUserDto = new UserDTO(userDao.getUser(userDto.getLogin()));
        assertEquals(userDto,newUserDto);
        roleDao.removeRole(new Role("user","afsdf"));
    }
    
    @Test
    public void testPageCounting(){
        RoleDAOImpl roleDao = new RoleDAOImpl();
        roleDao.addRole(new Role("user","afsdf"));
        UserService userService = new UserService();
        userService.addNewUser("asdby","asd","asd");
        userService.addNewUser("asdrty","asfd","asdd");
        assertEquals(userService.getPagesCount(1),(Integer)2);
        assertEquals(userService.getPagesCount(2),(Integer)1);
        roleDao.removeRole(new Role("user","afsdf"));
    }
    
    @Test
    public void testCounting(){
        RoleDAOImpl roleDao = new RoleDAOImpl();
        roleDao.addRole(new Role("user","afsdf"));
        UserService userService = new UserService();
        userService.addNewUser("asdby","asd","asd");
        userService.addNewUser("asdrty","asfd","asdd");
        assertEquals(userService.getCount(),(Integer)2);
        roleDao.removeRole(new Role("user","afsdf"));
    }
    
    @Test
    public void testGetting(){
        RoleDAOImpl roleDao = new RoleDAOImpl();
        roleDao.addRole(new Role("user","afsdf"));
        UserService userService = new UserService();
        userService.addNewUser("asdby","asd","asd");
        assertEquals(new UserDTO("asdby","asd","asd"),userService.getUser("asdby"));
        roleDao.removeRole(new Role("user","afsdf"));
    }
    
    @Test
    public void testRemoving(){
        RoleDAOImpl roleDao = new RoleDAOImpl();
        roleDao.addRole(new Role("user","afsdf"));
        UserService userService = new UserService();
        userService.addNewUser("asdby","asd","asd");
        userService.removeUser("asdby");
        assertNull(userService.getUser("asdby")); 
        roleDao.removeRole(new Role("user","afsdf"));
    }
    
    @Test
    public void testListing(){
        List<UserDTO> list = new ArrayList<UserDTO>();
        RoleDAOImpl roleDao = new RoleDAOImpl();
        roleDao.addRole(new Role("user","afsdf"));
        UserService userService = new UserService();
        userService.addNewUser("asdby","asd","asd");
        list.add(userService.getUser("asdby"));
        userService.addNewUser("asby","asd","asd");
        list.add(userService.getUser("asby"));
        assertEquals(list,userService.listUsers(1, 2));
        roleDao.removeRole(new Role("user","afsdf"));
    }
    
    @Test
    public void testValidating(){
        RoleDAOImpl roleDao = new RoleDAOImpl();
        roleDao.addRole(new Role("user","afsdf"));
        UserService userService = new UserService();
        userService.addNewUser("asdby","asd","asd");
        assertTrue(userService.userValidate("asdby", "asdby"));
        roleDao.removeRole(new Role("user","afsdf"));
    }
    
    @Test
    public void testChangingPass(){
        RoleDAOImpl roleDao = new RoleDAOImpl();
        roleDao.addRole(new Role("user","afsdf"));
        UserService userService = new UserService();
        userService.addNewUser("asdby","asd","asd");
        userService.changePass("asdby", "qwerty");
        assertTrue(userService.userValidate("asdby", "qwerty"));
        roleDao.removeRole(new Role("user","afsdf"));
    }
}
