package net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate;

import net.sam_solutions.courses.dkrauchanka.utils.HSQLDBUtil;
import org.junit.BeforeClass;
import org.apache.log4j.Logger;
import org.junit.Test;
import net.sam_solutions.courses.dkrauchanka.dao_impl_jdbc.RoleDAOImpl;
import net.sam_solutions.courses.dkrauchanka.domain.Role;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestRoleDAOImplJDBC {
    public static final Logger log = Logger.getLogger(TestRoleDAOImplJDBC.class);
    
    @BeforeClass
    public static void before(){
        HSQLDBUtil.getInstance().prepare();
    }
    
    @Test
    public void testAdding(){
        RoleDAOImpl roleDao = new RoleDAOImpl();
        Role role = new Role("admin","asdas, dfasd");
        roleDao.addRole(role);
        String rol = role.getRole();
        Role newRole = roleDao.getRole(rol);
        assertEquals(role,newRole);
        roleDao.removeRole(role);
    }
    
    @Test
    public void removingRole(){
        RoleDAOImpl roleDao = new RoleDAOImpl();
        Role role = new Role("admin","asdas, dfasd");
        roleDao.addRole(role);
        String rol = role.getRole();
        roleDao.removeRole(role);
        Role getRole = roleDao.getRole(rol);
        assertNull(getRole);
    }
    
    @Test
    public void updatingRole(){
        RoleDAOImpl roleDao = new RoleDAOImpl();
        Role role = new Role("admin","asdas, dfasd");
        roleDao.addRole(role);
        String rol = role.getRole();
        role.setValue("ussgdfsdfg");
        roleDao.addRole(role);
        Role newRole = roleDao.getRole(rol);
        assertEquals(role,newRole);
        roleDao.removeRole(role);
    }
}
