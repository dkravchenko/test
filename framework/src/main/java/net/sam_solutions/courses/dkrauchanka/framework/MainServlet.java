package net.sam_solutions.courses.dkrauchanka.framework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sam_solutions.courses.dkrauchanka.framework.Action;
import net.sam_solutions.courses.dkrauchanka.framework.ActionFactory;

public class MainServlet extends HttpServlet {

     protected ActionFactory factory = new ActionFactory();

     public MainServlet() {
          super();
     }

     protected String getActionName(HttpServletRequest request) {
          String path = request.getServletPath();
          path = path.substring(1,path.length());
          if(path.indexOf("/") <= 0)
        	  return path;
          else
        	  return path.substring(0, path.indexOf("/"));
     }

     public void service(HttpServletRequest request, HttpServletResponse response) 
       throws 
     	ServletException, IOException {
          Action action = factory.create(getActionName(request));
          String url = action.perform(request, response);
          if (url != null)
               getServletContext().getRequestDispatcher(url).forward(request, response);
     }
}