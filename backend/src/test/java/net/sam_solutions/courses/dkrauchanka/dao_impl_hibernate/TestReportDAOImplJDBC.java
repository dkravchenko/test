package net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate;

import net.sam_solutions.courses.dkrauchanka.utils.HSQLDBUtil;
import org.junit.BeforeClass;
import java.util.Date;
import net.sam_solutions.courses.dkrauchanka.utils.Password;
import org.apache.log4j.Logger;
import org.junit.Test;
import net.sam_solutions.courses.dkrauchanka.dao_impl_jdbc.RoleDAOImpl;
import net.sam_solutions.courses.dkrauchanka.domain.User;
import net.sam_solutions.courses.dkrauchanka.domain.Role;
import net.sam_solutions.courses.dkrauchanka.dao_impl_jdbc.ReportDAOImpl;
import net.sam_solutions.courses.dkrauchanka.domain.Report;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestReportDAOImplJDBC {
    public static final Logger log = Logger.getLogger(TestRoleDAOImplJDBC.class);
    
    @BeforeClass
    public static void before(){
        HSQLDBUtil.getInstance().prepare();
    }
    
    @Test
    public void testAdding(){
        Role role = new Role("admin","asdas, dfasd");
        User user = new User("demon13.by",Password.hashPassword(Password.hashPassword("Scorpion")+Password.SALT),"Denis","Kravchenko",role);
        ReportDAOImpl reportDao = new ReportDAOImpl();
        Report report = new Report(null,new Date(),56,12,user);
        reportDao.addReport(report);
        Integer id = report.getId();
        log.fatal(id);
        Report newReport = reportDao.getReport(id);
        assertEquals(report,newReport);
        reportDao.removeReport(report);
        RoleDAOImpl roleDao = new RoleDAOImpl();
        roleDao.removeRole(role);
    }
    
    @Test
    public void removingUser(){
        Role role = new Role("admin","asdas, dfasd");
        User user = new User("demon13.by",Password.hashPassword(Password.hashPassword("Scorpion")+Password.SALT),"Denis","Kravchenko",role);
        ReportDAOImpl reportDao = new ReportDAOImpl();
        Report report = new Report(null,new Date(),56,12,user);
        reportDao.addReport(report);
        Integer id = report.getId();
        reportDao.removeReport(report);
        Report newReport = reportDao.getReport(id);
        assertNull(newReport);
        RoleDAOImpl roleDao = new RoleDAOImpl();
        roleDao.removeRole(role);
    }
    
    @Test
    public void updatingUser(){
        Role role = new Role("admin","asdas, dfasd");
        User user = new User("demon13.by",Password.hashPassword(Password.hashPassword("Scorpion")+Password.SALT),"Denis","Kravchenko",role);
        ReportDAOImpl reportDao = new ReportDAOImpl();
        Report report = new Report(null,new Date(),56,12,user);
        reportDao.addReport(report);
        Integer id = report.getId();
        report.setDoneHours(123);
        reportDao.addReport(report);
        Report newReport = reportDao.getReport(id);
        assertEquals(report,newReport);
        reportDao.removeReport(report);
        RoleDAOImpl roleDao = new RoleDAOImpl();
        roleDao.removeRole(role);
    }
}
