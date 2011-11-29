package net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate;


import java.util.Date;
import net.sam_solutions.courses.dkrauchanka.domain.Meeting;
import java.sql.Connection;
import net.sam_solutions.courses.dkrauchanka.domain.User;
import org.apache.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class UserDAOImplTest{
    public static Logger log = Logger.getLogger(UserDAOImplTest.class);
    public static Connection connection = null;
        
    /*@BeforeClass
    public static void beforeTesting(){
        try {
            connection = DriverManager.getConnection("jdbc:hsqldb:file:dbpath/dbname", "SA", "");
            Statement statement = connection.createStatement();
            BufferedReader reader = new BufferedReader(new FileReader("db.sql"));
            //String sql = "CREATE  TABLE users (users_login VARCHAR(45),users_pass VARCHAR(40) ,users_FN VARCHAR(45),users_LN VARCHAR(45) ,users_role VARCHAR(45), PRIMARY KEY (users_login))";
            String line;
            while ((line = reader.readLine()) != null) {
                statement.executeUpdate(line);
            }
            reader.close();
        } catch (SQLException e) {
            log.fatal("Cannot connect to HSQL database");
        }
        catch(FileNotFoundException e){
            log.fatal("Cannot load sql-script");
        }
        catch(IOException e){
            log.fatal("Cannot read file");
        }
    }
    
    @AfterClass
    public static void afterTesting(){
        try{
            String sql = "SHUTDOWN";
            Statement stm = connection.createStatement();
            stm.execute(sql);
            connection.close();
        }
        catch(SQLException e){
            
        }
    }*/
	
    @Test
    public void testAddingUser(){
	UserDAOImpl userDao = new UserDAOImpl();
	User user = new User("1","asd","asd","asd","admin");
	userDao.addUser(user);
    	String login = user.getLogin();
	User newUser = userDao.getUser(login);
	assertEquals(user,newUser);
	userDao.removeUser(user);
    }
	
    @Test
    public void testRemovingUser(){
	UserDAOImpl userDao = new UserDAOImpl();
	User user = new User("2","asd","asd","asd","admin");
	userDao.addUser(user);
	String login = user.getLogin();
	userDao.removeUser(user);
	User newUser = userDao.getUser(login);
	assertNull(newUser);
    }
	
    @Test
    public void testUpdatingUser(){
	UserDAOImpl userDao = new UserDAOImpl();
	User user = new User("3","asd","asd","asd","admin");
	userDao.addUser(user);
	user.setFirstName("aaa");
        String login = user.getLogin();
	userDao.addUser(user);
	User newUser = userDao.getUser(login);
	assertEquals(newUser.getLogin(),user.getLogin());
	userDao.removeUser(user);
    }
    
    /*@Test
    public void testAddingUserWithMeeting(){
        UserDAOImpl userDao = new UserDAOImpl();
	User user = new User("demon13@np.by","asd","asd","asd","admin");
        User user1 = new User("demon13@rambler.ru","asd","asd","asd","admin");
        Meeting meeting = new Meeting(null,"aasd","asd",new Date(),user);
        meeting.addUser(user1);
        user.setMeeting(meeting);
        userDao.addUser(user);
        User newUser = userDao.getUser(user.getLogin());
        assertEquals(user,newUser);
        userDao.removeUser(user);
    }*/
}
