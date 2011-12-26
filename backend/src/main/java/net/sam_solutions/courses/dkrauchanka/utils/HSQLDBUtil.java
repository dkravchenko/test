package net.sam_solutions.courses.dkrauchanka.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;

public class HSQLDBUtil {
    private static HSQLDBUtil instance;
    private Connection connection;
    public static final Logger log = Logger.getLogger(HSQLDBUtil.class);
    
    private HSQLDBUtil(){
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:mem:db", "SA", "");
            Statement statement = connection.createStatement();
            BufferedReader reader = new BufferedReader(new FileReader("db.sql"));
            String line;
            while((line = reader.readLine()) != null){
                log.fatal(line);
                statement.execute(line);
            }
            reader.close();
        }
        catch (FileNotFoundException e) {
            log.error("1.sql not found");
        }
        catch (IOException e) {
            log.error("Cannot read db.sql");
        }
        catch (ClassNotFoundException e) {
            log.error("Driver class not found");
        }catch (SQLException e) {
            log.error("SQL error");
        }
        
    }
    
    public static synchronized HSQLDBUtil getInstance(){
        if(instance == null)
            instance = new HSQLDBUtil();
        return instance;
    }
    
    public void prepare(){
        try{
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM roles");
        }
        catch(SQLException e){
            log.error("Cannot prepare DB");
        }
    }
    
    public Connection getConnection(){
        return connection;
    }
}
