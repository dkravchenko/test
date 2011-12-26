package net.sam_solutions.courses.dkrauchanka.service;

import net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate.RoleDAOImpl;
import net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate.UserDAOImpl;
import net.sam_solutions.courses.dkrauchanka.domain.Role;
import net.sam_solutions.courses.dkrauchanka.dto.UserDTO;
import org.apache.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

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
}
