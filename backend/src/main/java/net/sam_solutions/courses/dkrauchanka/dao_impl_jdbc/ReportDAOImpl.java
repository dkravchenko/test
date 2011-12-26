package net.sam_solutions.courses.dkrauchanka.dao_impl_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sam_solutions.courses.dkrauchanka.dao.ReportDAO;
import net.sam_solutions.courses.dkrauchanka.domain.Report;
import net.sam_solutions.courses.dkrauchanka.domain.User;
import net.sam_solutions.courses.dkrauchanka.utils.ConnectionPoolUtil;
import org.apache.log4j.Logger;

public class ReportDAOImpl implements ReportDAO{
    public static final Logger log = Logger.getLogger(ReportDAOImpl.class);
    
    @Override
    public void addReport(Report report) {
        Connection conn = ConnectionPoolUtil.getInstance().getConnection();
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM reports WHERE reports_id_reports=?;";
        try{
            ResultSet rs = null;
            if(report.getId() != null){
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, report.getId());
                rs = stmt.executeQuery();
            }
            UserDAOImpl userDao = new UserDAOImpl();
            userDao.addUser(report.getUser());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
            if(rs != null && rs.isBeforeFirst()){
                sql = "UPDATE reports SET reports_date=?, reports_working_hours=?,reports_done_hours=?, reports_users_login=? WHERE reports_id_reports=?;";
                stmt = conn.prepareStatement(sql);
                //??
                stmt.setString(1, sdf.format(report.getDateOfReport()).toString());
                stmt.setInt(2, report.getWorgingHours());
                stmt.setInt(3, report.getDoneHours());
                stmt.setString(4, report.getUser().getLogin());
                stmt.setInt(5, report.getId());
                stmt.execute();
            }
            else{
                sql = "INSERT INTO reports VALUES (null,?,?,?,?);";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, sdf.format(report.getDateOfReport()).toString());
                stmt.setInt(2, report.getWorgingHours());
                stmt.setInt(3, report.getDoneHours());
                stmt.setString(4, report.getUser().getLogin());
                stmt.execute();
                sql = "SELECT MAX(reports_id_reports) FROM reports;";
                stmt = conn.prepareStatement(sql);
                rs = stmt.executeQuery();
                rs.next();
                report.setId(rs.getInt(1));
            }
        }
        catch(SQLException e){
            log.error("Connot add report to the database");
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
    public List<Report> listReport(int page, int count) {
        List<Report> temp = null;
        Connection conn = ConnectionPoolUtil.getInstance().getConnection();
        String sql = "SELECT * FROM reports;";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if(rs != null){
                temp = new ArrayList<Report>();
                while(rs.next()){
                    UserDAOImpl userDao = new UserDAOImpl();
                    User user = userDao.getUser(rs.getString("reports_users_login"));
                    Report report = new Report(rs.getInt("reports_id_reports"),rs.getDate("reports_date"),rs.getInt("reports_working_hours"),rs.getInt("reports_done_hours"),user);
                    temp.add(report);
                }
            }
            return temp;
        }
        catch(SQLException e){
            log.error("Cannot list reports");
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
    public void removeReport(Report report) {
        Connection conn = ConnectionPoolUtil.getInstance().getConnection();
        String sql = "DELETE FROM reports WHERE reports_id_reports=?;";
        log.info(sql);
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, report.getId());
            stmt.execute();
        }
        catch(SQLException e){
            log.error("Cannot delete Report");
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
    public Report getReport(Integer id) {
        Report report = null;
        Connection conn = ConnectionPoolUtil.getInstance().getConnection();
        String sql = "SELECT * FROM reports WHERE reports_id_reports=?;";
        log.info(sql);
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.isBeforeFirst()){
                rs.next();
                UserDAOImpl userDao = new UserDAOImpl();
                User user = userDao.getUser(rs.getString("reports_users_login"));
                report = new Report(rs.getInt("reports_id_reports"),rs.getDate("reports_date"),rs.getInt("reports_working_hours"),rs.getInt("reports_done_hours"),user);
            }
            return report;
        }
        catch(SQLException e){
            log.error("Cannot get Role");
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
    
}
