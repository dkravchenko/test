package net.sam_solutions.courses.dkrauchanka.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.sam_solutions.courses.dkrauchanka.framework.Action;
import net.sam_solutions.courses.dkrauchanka.framework.SessionUser;

public class LogoutController implements Action{

    @Override
    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        SessionUser user = (SessionUser)session.getAttribute("user");
        if(user != null){
            session.removeAttribute("user");
        }
        return "/WEB-INF/jsp/login.jsp";
    }
    
}
