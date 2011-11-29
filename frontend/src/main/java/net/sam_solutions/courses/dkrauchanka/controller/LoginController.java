package net.sam_solutions.courses.dkrauchanka.controller;

import javax.servlet.http.Cookie;
import net.sam_solutions.courses.dkrauchanka.framework.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate.UserDAOImpl;
import net.sam_solutions.courses.dkrauchanka.domain.User;
import net.sam_solutions.courses.dkrauchanka.framework.SessionUser;
import net.sam_solutions.courses.dkrauchanka.utils.Password;
import org.apache.log4j.Logger;

public class LoginController implements Action{
    public static final Logger log = Logger.getLogger(LoginController.class);
	public String perform(HttpServletRequest request){
            String login = (String)request.getParameter("login");
            String pass = (String)request.getParameter("pass");
            if(login == null || login.equals("") || pass == null || pass.equals("")){
		return "/WEB-INF/jsp/login.jsp";
            }
            else{
                UserDAOImpl userDao = new UserDAOImpl();
                User user = userDao.getUser(login);
                if (user != null && user.getPass().equals(Password.hashPassword(Password.hashPassword(pass)+Password.SALT)))
                {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", new SessionUser(user.getFirstName(),user.getLastName(),user.getRole()));
                    return "/WEB-INF/jsp/hello.jsp";
                }
                else
                    return "/WEB-INF/jsp/login.jsp";
            }
	}
}
