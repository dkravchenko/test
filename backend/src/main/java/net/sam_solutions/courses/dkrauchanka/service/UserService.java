package net.sam_solutions.courses.dkrauchanka.service;

import java.util.List;
import net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate.RoleDAOImpl;
import net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate.UserDAOImpl;
import net.sam_solutions.courses.dkrauchanka.domain.Role;
import net.sam_solutions.courses.dkrauchanka.domain.User;
import net.sam_solutions.courses.dkrauchanka.dto.UserDTO;
import net.sam_solutions.courses.dkrauchanka.utils.Password;

public class UserService {
    public void addNewUser(String login, String fn, String ln){
        RoleDAOImpl roleDao = new RoleDAOImpl();
        Role role = roleDao.getRole("user");
        User user = new User(login,Password.hashPassword(Password.hashPassword(login)+Password.SALT),fn,ln,role);
        UserDAOImpl userDao = new UserDAOImpl();
        userDao.addUser(user);
    }
    
    public Integer getPagesCount(int onPage){
        UserDAOImpl userDao = new UserDAOImpl();
        Long count = userDao.countUser();
        return (int)Math.ceil((double)count/onPage);
    }
    
    public Integer getCount(){
        UserDAOImpl userDao = new UserDAOImpl();
        return (int)Math.ceil(userDao.countUser());
    }
    
    public UserDTO getUser(String login){
        UserDAOImpl userDao = new UserDAOImpl();
        User user = userDao.getUser(login);
        return new UserDTO(user);
    }
    
    public void removeUser(String login){
        UserDAOImpl userDao = new UserDAOImpl();
        userDao.removeUser(new User(login,null,null,null,null));
    }
    
    public List<UserDTO> listUsers(int page, int count){
        UserDAOImpl userDao = new UserDAOImpl();
        List<User> list = userDao.listUser(page,10);
        List<UserDTO> listDto = UserDTO.userToUserDTOList(list);
        return listDto;
    }
    
    public void changePass(String login, String pass){
        UserDAOImpl userDao = new UserDAOImpl();
        User user = userDao.getUser(login);
        user.setPass(Password.hashPassword(Password.hashPassword(pass)+Password.SALT));
        userDao.addUser(user);
    }
}
