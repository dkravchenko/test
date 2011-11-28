package net.sam_solutions.courses.dkrauchanka.framework;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

public class MainServlet extends HttpServlet {     
     private static final Logger log = Logger.getLogger(MainServlet.class);
    
     private String getActionName(HttpServletRequest request) {
          String path = request.getServletPath();
          if(path.lastIndexOf("/") <= 0 && path.length() <= 1)
        	  return "index";
          else
        	  return path.substring(1, path.length());
     }

     private SessionUser getSessionUser(HttpServletRequest request){
         HttpSession session = request.getSession(true);
         SessionUser user = (SessionUser)session.getAttribute("user");
         return user;
     }
     
     public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 Action action = null;
         String requestMap = getActionName(request);
         SessionUser user = getSessionUser(request);
         if(user == null){
             requestMap = "login";
             user = new SessionUser(null,null,"guest");
         }
    	 try{
             if(!SecurityManager.getInstance(getServletContext().getRealPath("WEB-INF/classes/security.xml")).isAccessable(requestMap, user.getRole())){
                 requestMap = "index";
             }
             action = (Action)Class.forName(ControllerManager.getInstance(getServletContext().getRealPath("WEB-INF/classes/controllers.xml")).getController(requestMap)).newInstance();
    	 }
    	 catch (FileNotFoundException e){
            action = new ErrorController(500,"Internal server error",e.getStackTrace());
            log.fatal("File not found");
         } 
    	 catch (ParserConfigurationException e){
        	 action = new ErrorController(500,"Internal server error",e.getStackTrace());
                 log.fatal("Bad parser configuration");
         } 
    	 catch (SAXException e){
        	 action = new ErrorController(500,"Internal server error",e.getStackTrace());
                 log.fatal("Error while parsing xml");
         } 
    	 catch (IOException e){
        	 action = new ErrorController(500,"Internal server error",e.getStackTrace());
                 log.fatal("Error reading file");
         }
    	 catch (ClassCastException e){
    		 action = new ErrorController(500,"Internal server error",e.getStackTrace());
                 log.fatal("Error while casting class");
    	 }
    	 catch (ClassNotFoundException e){
    		 action = new ErrorController(500,"Internal server error",e.getStackTrace());
                 log.fatal("Class not found");
    	 }
    	 catch (InstantiationException e){
    		 action = new ErrorController(500,"Internal server error",e.getStackTrace());
                 log.fatal("Insctantiation error");
    	 }
    	 catch(IllegalAccessException e){
    		 action = new ErrorController(500,"Internal server error",e.getStackTrace());
                 log.fatal("Illegal access");
    	 }
    	 catch(NullPointerException e){
    		 action = new ErrorController(500,"Internal server error",e.getStackTrace());
                 log.fatal("Null pointer");
    	 }
          String url = action.perform(request);
          if (url != null)
              log.info("SUCCESS");
               getServletContext().getRequestDispatcher(url).forward(request, response);
     }
}