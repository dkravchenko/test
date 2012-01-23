package net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import net.sam_solutions.courses.dkrauchanka.utils.HSQLDBUtil;
import org.junit.BeforeClass;
import org.apache.log4j.Logger;
import org.junit.Test;
import net.sam_solutions.courses.dkrauchanka.domain.Role;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestRoleDAOImplHibernate {
    public static final Logger log = Logger.getLogger(TestRoleDAOImplHibernate.class);
    
    @BeforeClass
    public static void before(){
        BeanFactory factory = new XmlBeanFactory(new ClassPathResource("spring.xml"));
        HSQLDBUtil util = (HSQLDBUtil) factory.getBean("HSQLDBUtil");
        util.prepare();
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
        role.setValue("userdv");
        roleDao.addRole(role);
        Role newRole = roleDao.getRole(rol);
        assertEquals(role,newRole);
        roleDao.removeRole(role);
    }
}
