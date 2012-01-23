package net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate;

import net.sam_solutions.courses.dkrauchanka.utils.HSQLDBUtil;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.junit.BeforeClass;
import java.util.Date;
import net.sam_solutions.courses.dkrauchanka.utils.Password;
import org.apache.log4j.Logger;
import org.junit.Test;
import net.sam_solutions.courses.dkrauchanka.domain.User;
import net.sam_solutions.courses.dkrauchanka.domain.Role;
import net.sam_solutions.courses.dkrauchanka.domain.Report;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestReportDAOImplHibernate {
    public static final Logger log = Logger.getLogger(TestReportDAOImplHibernate.class);
    
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
        ReportDAOImpl reportDao = new ReportDAOImpl();
        Report report = new Report(null,new Date(),56,12,user);
        reportDao.addReport(report);
        Integer id = report.getId();
        Report newReport = reportDao.getReport(id);
        assertEquals(report,newReport);
        reportDao.removeReport(report);
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
        ReportDAOImpl reportDao = new ReportDAOImpl();
        Report report = new Report(null,new Date(),56,12,user);
        reportDao.addReport(report);
        Integer id = report.getId();
        reportDao.removeReport(report);
        Report newReport = reportDao.getReport(id);
        assertNull(newReport);
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
        ReportDAOImpl reportDao = new ReportDAOImpl();
        Report report = new Report(null,new Date(),56,12,user);
        reportDao.addReport(report);
        Integer id = report.getId();
        report.setDoneHours(123);
        reportDao.addReport(report);
        Report newReport = reportDao.getReport(id);
        assertEquals(report,newReport);
        reportDao.removeReport(report);
        roleDao.removeRole(role);
    }
}
