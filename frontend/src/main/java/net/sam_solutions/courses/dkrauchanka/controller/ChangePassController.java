package net.sam_solutions.courses.dkrauchanka.controller;

import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import net.sam_solutions.courses.dkrauchanka.framework.Action;
import net.sam_solutions.courses.dkrauchanka.service.UserService;

public class ChangePassController implements Action{

    @Override
    public String perform(HttpServletRequest request) {
        ResourceBundle rs = ResourceBundle.getBundle("text", request.getLocale());
        UserService userService = new UserService();
        String pass = request.getParameter("pass");
        String confirm = request.getParameter("confirm");
        String error = null;
        String login = request.getParameter("login");
        if(pass == null || pass.equals("")){
            error = rs.getString("error.passnull");
        } else if(confirm == null || confirm.equals("")){
            error = rs.getString("error.confirmnull");
        } else if(!confirm.equals(pass)){
            error = rs.getString("error.confirmfailed");
        } else {
            userService.changePass(login, pass);
            return "/WEB-INF/jsp/hello.jsp";
        }
        if(error != null){
            request.setAttribute("error", error);
            request.setAttribute("login", login);
        }
        return "/WEB-INF/jsp/changepass.jsp";
    }
    
}
