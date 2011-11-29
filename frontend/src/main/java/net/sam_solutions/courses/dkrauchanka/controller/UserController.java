package net.sam_solutions.courses.dkrauchanka.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate.UserDAOImpl;
import net.sam_solutions.courses.dkrauchanka.domain.User;
import net.sam_solutions.courses.dkrauchanka.dto.UserDTO;
import net.sam_solutions.courses.dkrauchanka.framework.Action;

public class UserController implements Action{
    public String perform(HttpServletRequest request){
        String login = request.getParameter("login");;
        String fn;
        String ln;
        UserDAOImpl userDao = new UserDAOImpl();
        if(request.getParameter("update") != null){
            fn = request.getParameter("fn");
            ln = request.getParameter("ln");
            if(fn != null && !fn.equals("") && ln != null && !ln.equals("")){
                User user = userDao.getUser(login);
                user.setFirstName(fn);
                user.setLastName(ln);
                userDao.addUser(user);
            }
        }
        if(login != null && !login.equals("")){
            User user = userDao.getUser(login);
            String action = request.getParameter("action");
            if(action.equals("delete")){
                userDao.removeUser(user);
            }
            else if (action.equals("update")){
                User userToUpdate = userDao.getUser(login);
                UserDTO userToUpdateDto = new UserDTO(userToUpdate);
                request.setAttribute("userToUpdate", userToUpdateDto);
            }
        }
        
        List<User> list = userDao.listUser();
        List<UserDTO> dto = UserDTO.userToUserDTOList(list);
        request.setAttribute("userList", dto);
        
        return "/WEB-INF/jsp/user.jsp";
    }
}
