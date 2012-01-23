package net.sam_solutions.courses.dkrauchanka.dao_impl_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.sam_solutions.courses.dkrauchanka.dao.TaskDAO;
import net.sam_solutions.courses.dkrauchanka.domain.Report;
import net.sam_solutions.courses.dkrauchanka.domain.Task;
import net.sam_solutions.courses.dkrauchanka.domain.User;
import net.sam_solutions.courses.dkrauchanka.utils.ConnectionPoolUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class TaskDAOImpl implements TaskDAO{
    public static final Logger log = Logger.getLogger(TaskDAOImpl.class);
    private BeanFactory factory = new XmlBeanFactory(new ClassPathResource("spring.xml"));
    private ConnectionPoolUtil util = (ConnectionPoolUtil) factory.getBean("ConnectionPoolUtil");
    
    @Override
    public void addTask(Task task) {
        Connection conn = util.getConnection();
        try{
            ResultSet rs = null;
            String sql = null;
            PreparedStatement stmt = null;
            if(task.getId() != null){
                sql = "SELECT * FROM tasks WHERE tasks_id_task=?;";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, task.getId());
                rs = stmt.executeQuery();
            }
            UserDAOImpl userDao = new UserDAOImpl();
            ReportDAOImpl reportDao = new ReportDAOImpl();
            userDao.addUser(task.getUser());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
            if(rs != null && rs.isBeforeFirst()){
                sql = "UPDATE tasks SET tasks_title=?, tasks_text=?,tasks_hours_todo=?, tasks_status=?, tasks_end_time=?, tasks_users_login=? WHERE tasks_id_task=?;";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, task.getTitle());
                stmt.setString(2, task.getText());
                stmt.setInt(3, task.getHoursToDo());
                stmt.setString(4, task.getStatus());
                stmt.setString(5, sdf.format(task.getEndTime()).toString());
                stmt.setString(6, task.getUser().getLogin());
                stmt.setInt(7, task.getId());
                stmt.executeUpdate();
            }
            else{
                sql = "INSERT INTO tasks VALUES (null,?,?,?,?,?,?);";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, task.getTitle());
                stmt.setString(2, task.getText());
                stmt.setInt(3, task.getHoursToDo());
                stmt.setString(4, task.getStatus());
                stmt.setString(5, sdf.format(task.getEndTime()).toString());
                stmt.setString(6, task.getUser().getLogin());
                stmt.execute();
                sql = "SELECT MAX(tasks_id_task) FROM tasks;";
                Statement stm = conn.createStatement();
                rs = stm.executeQuery(sql);
                rs.next();
                task.setId(rs.getInt(1));
            }
            log.fatal(task.toString());
            List<Report> reports = task.getReports();
            if(reports != null){
                Iterator<Report> iter = reports.iterator();
                while(iter.hasNext()){
                    Report report = iter.next();
                    reportDao.addReport(report);
                    log.fatal(report.toString());
                    sql = "SELECT * FROM reports_has_tasks WHERE treports_id_reports=? AND rtasks_id_task=?;";
                    stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, report.getId());
                    stmt.setInt(2, task.getId());
                     log.fatal(report.getId());
                    log.fatal(task.getId());
                    rs = stmt.executeQuery();
                   
                    if(!rs.isBeforeFirst()){
                        sql = "INSERT INTO reports_has_tasks VALUES(?,?);";
                        stmt = conn.prepareStatement(sql);
                        stmt.setInt(1, report.getId());
                        stmt.setInt(2, task.getId());
                        stmt.execute();
                    }
                }
            }
        }
        catch(SQLException e){
            log.error("Connot add task to the database");
        }
        finally{
            try{
                conn.close();
            }
            catch(SQLException e){
                log.error("Cannot release connection");
            }
        }
    }

    @Override
    public List<Task> listTask(int page, int count) {
        List<Task> temp = null;
        Connection conn = util.getConnection();
        String sql = "SELECT tasks_id_task, tasks_title,tasks_text,tasks_hours_todo,tasks_status,tasks_end_time,tasks_users_login,treports_id_reports FROM tasks INNER JOIN reports_has_tasks ON tasks_id_task=rtasks_id_task;";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if(rs.isBeforeFirst()){
                temp = new ArrayList<Task>();
                Integer tempId = null;
                ReportDAOImpl reportDao = new ReportDAOImpl();
                UserDAOImpl userDao = new UserDAOImpl();
                while(rs.next()){
                    tempId = rs.getInt("tasks_id_task");
                    User user = userDao.getUser(rs.getString("tasks_users_login"));
                    Task task = new Task(rs.getInt("tasks_id_task"),rs.getString("tasks_title"),rs.getString("tasks_text"),rs.getInt("tasks_hours_todo"),rs.getString("tasks_status"),rs.getDate("tasks_end_time"),user);
                    List<Report> reports = new ArrayList<Report>();
                    while(tempId == rs.getInt("tasks_id_task")){
                        Report report = reportDao.getReport(rs.getInt("treports_id_reports"));
                        reports.add(report);
                        rs.next();
                    }
                    task.setReports(reports);
                    temp.add(task);
                }
            }
            return temp;
        }
        catch(SQLException e){
            log.error("Cannot list tasks");
            return null;
        }
        finally{
            try{
                conn.close();
            }
            catch(SQLException e){
                log.error("Cannot release connection");
            }
        }
    }
    
    public List<Task> listTaskByUser(User us) {
        List<Task> temp = null;
        Connection conn = util.getConnection();
        String sql = "SELECT tasks_id_task, tasks_title,tasks_text,tasks_hours_todo,tasks_status,tasks_end_time,tasks_users_login,treports_id_reports FROM tasks INNER JOIN reports_has_tasks ON tasks_id_task=rtasks_id_task WHERE tasks_users_login=?;";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, us.getLogin());
            ResultSet rs = stmt.executeQuery();
            if(rs.isBeforeFirst()){
                temp = new ArrayList<Task>();
                Integer tempId = null;
                ReportDAOImpl reportDao = new ReportDAOImpl();
                UserDAOImpl userDao = new UserDAOImpl();
                while(rs.next()){
                    tempId = rs.getInt("tasks_id_task");
                    Task task = new Task(rs.getInt("tasks_id_task"),rs.getString("tasks_title"),rs.getString("tasks_text"),rs.getInt("tasks_hours_todo"),rs.getString("tasks_status"),rs.getDate("tasks_end_time"),us);
                    List<Report> reports = new ArrayList<Report>();
                    while(tempId == rs.getInt("tasks_id_task")){
                        Report report = reportDao.getReport(rs.getInt("treports_id_reports"));
                        reports.add(report);
                        rs.next();
                    }
                    task.setReports(reports);
                    temp.add(task);
                }
            }
            return temp;
        }
        catch(SQLException e){
            log.error("Cannot list tasks");
            return null;
        }
        finally{
            try{
                conn.close();
            }
            catch(SQLException e){
                log.error("Cannot release connection");
            }
        }
    }

    @Override
    public Task getTask(Integer id) {
        Task task = null;
        Connection conn = util.getConnection();
        String sql = "SELECT tasks_id_task, tasks_title,tasks_text,tasks_hours_todo,tasks_status,tasks_end_time,tasks_users_login,rtasks_id_task,treports_id_reports FROM tasks INNER JOIN reports_has_tasks ON tasks_id_task=rtasks_id_task WHERE tasks_id_task=?;";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs != null){
                rs.next();
                ReportDAOImpl reportDao = new ReportDAOImpl();
                UserDAOImpl userDao = new UserDAOImpl();
                Integer tempId = rs.getInt("tasks_id_task");//12
                User user = userDao.getUser(rs.getString("tasks_users_login"));
                task = new Task(rs.getInt("tasks_id_task"),rs.getString("tasks_title"),rs.getString("tasks_text"),rs.getInt("tasks_hours_todo"),rs.getString("tasks_status"),rs.getDate("tasks_end_time"),user);
                List<Report> reports = new ArrayList<Report>();
                Report report = reportDao.getReport(rs.getInt("treports_id_reports"));
                reports.add(report);
                while(rs.next() && tempId == rs.getInt("tasks_id_task") ){
                    report = reportDao.getReport(rs.getInt("treports_id_reports"));
                    reports.add(report);
                }
                task.setReports(reports);
            }
            log.fatal("2");
            return task;
        }
        catch(SQLException e){
            log.error("Cannot get Tasks");
            return null;
        }
        finally{
            try{
                conn.close();
            }
            catch(SQLException e){
                log.error("Cannot release connection");
            }
        }
    }

    @Override
    public void removeTask(Task task) {
        Connection conn = util.getConnection();
        String sql = "DELETE FROM tasks WHERE tasks_id_task=?;";
        log.info(sql);
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();
        }
        catch(SQLException e){
            log.error("Cannot delete Task");
        }
        finally{
            try{
                conn.close();
            }
            catch(SQLException e){
                log.error("Cannot release connection");
            }
        }
    }
    
}
