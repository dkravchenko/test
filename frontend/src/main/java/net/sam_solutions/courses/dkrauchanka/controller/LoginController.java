package net.sam_solutions.courses.dkrauchanka.controller;

import javax.servlet.http.Cookie;
import net.sam_solutions.courses.dkrauchanka.framework.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate.UserDAOImpl;
import net.sam_solutions.courses.dkrauchanka.domain.User;
import net.sam_solutions.courses.dkrauchanka.framework.SessionUser;

public class LoginController implements Action{
	public String perform(HttpServletRequest request){
            String login = (String)request.getAttribute("login");
            String pass = (String)request.getAttribute("pass");
            if(login == null || login.equals("") || pass == null || pass.equals("")){
		return "/WEB-INF/jsp/login.jsp";
            }
            else{
                UserDAOImpl userDao = new UserDAOImpl();
                User user = userDao.isRegistered(login, pass);
                if (user != null){
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", new SessionUser(user.getFirstName(),user.getLastName(),user.getRole()));
                    return "/WEB-INF/jsp/index.jsp";
                }
                else
                    return "/WEB-INF/jsp/login.jsp";
            }
	}
}
