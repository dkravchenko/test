package net.sam_solutions.courses.dkrauchanka.controller;

import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import net.sam_solutions.courses.dkrauchanka.dto.UserDTO;
import net.sam_solutions.courses.dkrauchanka.framework.Action;
import net.sam_solutions.courses.dkrauchanka.service.UserService;

public class UserController implements Action{
    private Integer number = 10;
    
    public String perform(HttpServletRequest request){
        UserService userService = new UserService();
        String action = request.getParameter("action");
        if(action != null && !action.equals("")){
            String login = request.getParameter("login");
            if(login != null && !login.equals("")){
                if(action.equals("delete")){
                    userService.removeUser(login);
                }
                else if(action.equals("update")){
                    UserDTO userToUpdate = userService.getUser(login);
                    request.setAttribute("userToUpdate", userToUpdate);
                }
            }
        }
        else if(request.getParameter("user_submit") != null) {
            ResourceBundle rs = ResourceBundle.getBundle("text", request.getLocale());
            String error = null;
            String login = request.getParameter("user_login");
            String firstName = request.getParameter("user_fn");
            String lastName = request.getParameter("user_ln");
            if(login == null || login.equals("")){
                error = rs.getString("error.usernull");
                request.setAttribute("userToUpdate", new UserDTO(login,firstName,lastName));
                request.setAttribute("error", error);
            }
            else if(firstName == null || firstName.equals("")){
                error = rs.getString("error.fnnull");
                request.setAttribute("userToUpdate", new UserDTO(login,firstName,lastName));
                request.setAttribute("error", error);
            }
            else if(lastName == null || lastName.equals("")){
                error = rs.getString("error.lnnull");
                request.setAttribute("userToUpdate", new UserDTO(login,firstName,lastName));
                request.setAttribute("error", error);
            }
            else {
                userService.addNewUser(login, firstName, lastName);
            }
        }
        Integer page = 1;
        if(request.getParameter("page") != null && !request.getParameter("page").equals("")){
            page = Integer.valueOf(request.getParameter("page"));
        }
        List<UserDTO> list = userService.listUsers(page, number);
        request.setAttribute("userList", list);
        request.setAttribute("currentPage", page);
        request.setAttribute("pages",userService.getPagesCount(number));
        return "/WEB-INF/jsp/user.jsp";
    }
}
