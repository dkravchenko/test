package net.sam_solutions.courses.dkrauchanka.service;

import net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate.ReportDAOImpl;
import net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate.RoleDAOImpl;
import net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate.UserDAOImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sam_solutions.courses.dkrauchanka.domain.Report;
import net.sam_solutions.courses.dkrauchanka.domain.Role;
import net.sam_solutions.courses.dkrauchanka.domain.User;
import net.sam_solutions.courses.dkrauchanka.dto.ReportDTO;
import org.apache.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestReportService {
    public static final Logger log = Logger.getLogger(TestUserService.class);
    
    @Test
    public void testListing(){
        List<ReportDTO> list = new ArrayList<ReportDTO>();
        RoleDAOImpl roleDao = new RoleDAOImpl();
        roleDao.addRole(new Role("user","afsdf"));
        User user = new User("qwe","asd","as","das",new Role("user","afsdf"));
        UserDAOImpl userDao = new UserDAOImpl();
        userDao.addUser(user);
        ReportService reportService = new ReportService();
        ReportDAOImpl reportDao = new ReportDAOImpl();
        Report report = new Report(null, new Date(), 123, 12, user);
        reportDao.addReport(report);
        list.add(new ReportDTO(report));
        report = new Report(null, new Date(), 1234, 12, user);
        reportDao.addReport(report);
        list.add(new ReportDTO(report));
        assertEquals(list,reportService.listReport(1, 2));
        roleDao.removeRole(new Role("user","afsdf"));
    }
    
    @Test
    public void testListingByUser(){
        List<ReportDTO> list = new ArrayList<ReportDTO>();
        RoleDAOImpl roleDao = new RoleDAOImpl();
        roleDao.addRole(new Role("user","afsdf"));
        User user = new User("qwe","asd","as","das",new Role("user","afsdf"));
        UserDAOImpl userDao = new UserDAOImpl();
        userDao.addUser(user);
        User otherUser = new User("qtywe","asd","as","das",new Role("user","afsdf"));
        userDao.addUser(otherUser);
        ReportService reportService = new ReportService();
        ReportDAOImpl reportDao = new ReportDAOImpl();
        Report report = new Report(null, new Date(), 123, 12, user);
        reportDao.addReport(report);
        list.add(new ReportDTO(report));
        report = new Report(null, new Date(), 1234, 12, user);
        reportDao.addReport(report);
        list.add(new ReportDTO(report));
        report = new Report(null, new Date(), 1234, 12, otherUser);
        reportDao.addReport(report);
        assertEquals(list,reportService.listReportByUser(1, 2, "qwe"));
        roleDao.removeRole(new Role("user","afsdf"));
    }
    
    @Test
    public void testPageCounting(){
        RoleDAOImpl roleDao = new RoleDAOImpl();
        roleDao.addRole(new Role("user","afsdf"));
        User user = new User("qwe","asd","as","das",new Role("user","afsdf"));
        UserDAOImpl userDao = new UserDAOImpl();
        userDao.addUser(user);
        ReportService reportService = new ReportService();
        ReportDAOImpl reportDao = new ReportDAOImpl();
        Report report = new Report(null, new Date(), 123, 12, user);
        reportDao.addReport(report);
        report = new Report(null, new Date(), 1234, 12, user);
        reportDao.addReport(report);
        assertEquals((Integer)2,reportService.getPagesCount(1));
        assertEquals((Integer)1,reportService.getPagesCount(2));
        roleDao.removeRole(new Role("user","afsdf"));
    }
    
    @Test
    public void testPageCountingByUser(){
        RoleDAOImpl roleDao = new RoleDAOImpl();
        roleDao.addRole(new Role("user","afsdf"));
        User user = new User("qwe","asd","as","das",new Role("user","afsdf"));
        UserDAOImpl userDao = new UserDAOImpl();
        userDao.addUser(user);
        User otherUser = new User("qtywe","asd","as","das",new Role("user","afsdf"));
        userDao.addUser(otherUser);
        ReportService reportService = new ReportService();
        ReportDAOImpl reportDao = new ReportDAOImpl();
        Report report = new Report(null, new Date(), 123, 12, user);
        reportDao.addReport(report);
        report = new Report(null, new Date(), 1234, 12, user);
        reportDao.addReport(report);
        report = new Report(null, new Date(), 1234, 12, otherUser);
        reportDao.addReport(report);
        assertEquals((Integer)2,reportService.getPagesCountByUser(1,"qwe"));
        assertEquals((Integer)1,reportService.getPagesCountByUser(1,"qtywe"));
        roleDao.removeRole(new Role("user","afsdf"));
    }
    
    @Test
    public void testGetting(){
        RoleDAOImpl roleDao = new RoleDAOImpl();
        roleDao.addRole(new Role("user","afsdf"));
        User user = new User("qwe","asd","as","das",new Role("user","afsdf"));
        UserDAOImpl userDao = new UserDAOImpl();
        userDao.addUser(user);
        User otherUser = new User("qtywe","asd","as","das",new Role("user","afsdf"));
        userDao.addUser(otherUser);
        ReportService reportService = new ReportService();
        ReportDAOImpl reportDao = new ReportDAOImpl();
        Report report = new Report(null, new Date(), 123, 12, user);
        reportDao.addReport(report);
        assertEquals(new ReportDTO(report),reportService.getReport(report.getId()));
        roleDao.removeRole(new Role("user","afsdf"));
    }
}
