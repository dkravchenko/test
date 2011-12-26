package net.sam_solutions.courses.dkrauchanka.dao_impl_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import net.sam_solutions.courses.dkrauchanka.dao.RoleDAO;
import net.sam_solutions.courses.dkrauchanka.domain.Role;
import net.sam_solutions.courses.dkrauchanka.utils.ConnectionPoolUtil;
import org.apache.log4j.Logger;

public class RoleDAOImpl implements RoleDAO{
    public static final Logger log = Logger.getLogger(RoleDAOImpl.class);
    @Override
    public void addRole(Role role) {
        Connection conn = ConnectionPoolUtil.getInstance().getConnection();
        try {
            String sql;
            PreparedStatement stmt;
            sql = "SELECT * FROM roles WHERE roles_role=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, role.getRole());
            ResultSet rs = stmt.executeQuery();
            if(rs.isBeforeFirst()){
                sql = "UPDATE roles SET roles_value=?  WHERE roles_role=?;";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, role.getValue());
                stmt.setString(2, role.getRole());
                stmt.execute();
            }
            else {
                sql = "INSERT INTO roles VALUES (?,?);";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, role.getRole());
                stmt.setString(2, role.getValue());
                stmt.execute();
            }
        }
        catch(SQLException e){
            log.error("Connot add role to the database");
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
    public List<Role> listRole() {
        List<Role> temp = null;
        Connection conn = ConnectionPoolUtil.getInstance().getConnection();
        String sql = "SELECT * FROM roles;";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if(rs != null){
                temp = new ArrayList<Role>();
                while(rs.next()){
                    Role role = new Role(rs.getString("roles_role"),rs.getString("roles_value"));
                    temp.add(role);
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
    public void removeRole(Role role) {
        Connection conn = ConnectionPoolUtil.getInstance().getConnection();
        String sql = "DELETE FROM roles WHERE roles_role=?;";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, role.getRole());
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
    public Role getRole(String rol) {
        Role role = null;
        Connection conn = ConnectionPoolUtil.getInstance().getConnection();
        String sql = "SELECT * FROM roles WHERE roles_role=?;";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, rol);
            ResultSet rs = stmt.executeQuery();
            if(rs != null){
                rs.next();
                role = new Role(rs.getString("roles_role"),rs.getString("roles_value"));
            }
            return role;
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
