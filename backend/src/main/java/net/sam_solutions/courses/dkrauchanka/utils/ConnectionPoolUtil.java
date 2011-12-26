package net.sam_solutions.courses.dkrauchanka.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.sql.DataSource;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;


public class ConnectionPoolUtil {
    public static final Logger log = Logger.getLogger(ConnectionPoolUtil.class);
    private static ConnectionPoolUtil instance;
    private DataSource dataSource;
    
    private ConnectionPoolUtil(){
        ResourceBundle rs = ResourceBundle.getBundle("jdbc");
        try {
            ObjectPool connectionPool = new GenericObjectPool(null);
            Class.forName(rs.getString("db.driver"));
            ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(rs.getString("db.url"),rs.getString("db.user"),rs.getString("db.pass"));
            PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory,connectionPool,null,null,false,true);
            dataSource = new PoolingDataSource(connectionPool);
         } catch (ClassNotFoundException e) {	            
             log.fatal("Connot establish db connection");	        
         }
    }
    
    public static synchronized ConnectionPoolUtil getInstance(){
        if(instance == null)
            instance = new ConnectionPoolUtil();
        return instance;
    }
    
    public Connection getConnection(){
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
        }
        catch(SQLException e){
            log.fatal("Cannot establish connection to database");
        }
        return conn;
    }
}
