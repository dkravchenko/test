package net.sam_solutions.courses.dkrauchanka.dao_impl_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import net.sam_solutions.courses.dkrauchanka.dao.UserDAO;
import net.sam_solutions.courses.dkrauchanka.domain.Role;
import net.sam_solutions.courses.dkrauchanka.domain.User;
import net.sam_solutions.courses.dkrauchanka.utils.ConnectionPoolUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class UserDAOImpl implements UserDAO{
    public static final Logger log = Logger.getLogger(UserDAOImpl.class);
    private BeanFactory factory = new XmlBeanFactory(new ClassPathResource("spring.xml"));
    private ConnectionPoolUtil util = (ConnectionPoolUtil) factory.getBean("ConnectionPoolUtil");
    
    @Override
    public void addUser(User user) {
        Connection conn = util.getConnection();
        String sql = "SELECT * FROM users WHERE users_login=?;";
        log.info(sql);
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getLogin());
            ResultSet rs = stmt.executeQuery();
            if(rs.isBeforeFirst()){
                RoleDAOImpl roleDao = new RoleDAOImpl();
                roleDao.addRole(user.getRole());
                sql = "UPDATE users SET users_pass=?, users_FN=?,users_LN=?, users_role=? WHERE users_login=?;";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, user.getPass());
                stmt.setString(2, user.getFirstName());
                stmt.setString(3, user.getLastName());
                stmt.setString(4, user.getRole().getRole());
                stmt.setString(5, user.getLogin());
                stmt.execute();
            }
            else {
                RoleDAOImpl roleDao = new RoleDAOImpl();
                roleDao.addRole(user.getRole());
                sql = "INSERT INTO users VALUES (?,?,?,?,?);";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, user.getLogin());
                stmt.setString(2, user.getPass());
                stmt.setString(3, user.getFirstName());
                stmt.setString(4, user.getLastName());
                stmt.setString(5, user.getRole().getRole());
                stmt.execute();
            }
        }
        catch(SQLException e){
            log.error("Connot add user to the database");
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
    public List<User> listUser(int page, int count) {
        List<User> temp = null;
        Connection conn = util.getConnection();
        String sql = "SELECT * FROM users;";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if(rs != null){
                temp = new ArrayList<User>();
                while(rs.next()){
                    RoleDAOImpl roleDao = new RoleDAOImpl();
                    Role role = roleDao.getRole(rs.getString("users_role"));
                    User user = new User(rs.getString("users_login"),rs.getString("users_pass"),rs.getString("users_FN"),rs.getString("users_LN"),role);
                    temp.add(user);
                }
            }
            return temp;
        }
        catch(SQLException e){
            log.error("Cannot list roles");
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
    public void removeUser(User user) {
        Connection conn = util.getConnection();
        String sql = "DELETE FROM users WHERE users_login=?;";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getLogin());
            stmt.execute();
        }
        catch(SQLException e){
            log.error("Cannot delete Role");
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
    public User getUser(String login) {
        User user = null;
        Connection conn = util.getConnection();
        String sql = "SELECT * FROM users WHERE users_login=?;";
        log.info(sql);
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if(rs != null){
                rs.next();
                RoleDAOImpl roleDao = new RoleDAOImpl();
                Role role = roleDao.getRole(rs.getString("users_role"));
                user = new User(rs.getString("users_login"),rs.getString("users_pass"),rs.getString("users_FN"),rs.getString("users_LN"),role);
            }
            return user;
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
