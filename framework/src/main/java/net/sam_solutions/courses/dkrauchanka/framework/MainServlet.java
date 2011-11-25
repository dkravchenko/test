package net.sam_solutions.courses.dkrauchanka.framework;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.omg.CORBA.Environment;
import org.xml.sax.SAXException;

public class MainServlet extends HttpServlet {     

     private String getActionName(HttpServletRequest request) {
          String path = request.getServletPath();
          if(path.lastIndexOf("/") <= 0 && path.length() <= 1)
        	  return "index";
          else
        	  return path.substring(1, path.length());
     }

     public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 Action action = null;
    	 try{
    		 action = (Action)Class.forName(ControllerManager.getInstance(getServletContext().getRealPath("WEB-INF/classes/controllers.xml")).getController(getActionName(request))).newInstance();
    	 }
    	 catch (FileNotFoundException e){
            action = new ErrorController(500,"Internal server error",e.getStackTrace());
         } 
    	 catch (ParserConfigurationException e){
        	 action = new ErrorController(500,"Internal server error",e.getStackTrace());
         } 
    	 catch (SAXException e){
        	 action = new ErrorController(500,"Internal server error",e.getStackTrace());
         } 
    	 catch (IOException e){
        	 action = new ErrorController(500,"Internal server error",e.getStackTrace());
         }
    	 catch (ClassCastException e){
    		 action = new ErrorController(500,"Internal server error",e.getStackTrace());
    	 }
    	 catch (ClassNotFoundException e){
    		 action = new ErrorController(500,"Internal server error",e.getStackTrace());
    	 }
    	 catch (InstantiationException e){
    		 action = new ErrorController(500,"Internal server error",e.getStackTrace());
    	 }
    	 catch(IllegalAccessException e){
    		 action = new ErrorController(500,"Internal server error",e.getStackTrace());
    	 }
    	 catch(NullPointerException e){
    		 action = new ErrorController(500,"Internal server error",e.getStackTrace());
    	 }
          String url = action.perform(request);
          if (url != null)
               getServletContext().getRequestDispatcher(url).forward(request, response);
     }
}