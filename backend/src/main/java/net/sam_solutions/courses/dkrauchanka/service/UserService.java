package net.sam_solutions.courses.dkrauchanka.service;

import java.util.List;
import net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate.RoleDAOImpl;
import net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate.UserDAOImpl;
import net.sam_solutions.courses.dkrauchanka.domain.Role;
import net.sam_solutions.courses.dkrauchanka.domain.User;
import net.sam_solutions.courses.dkrauchanka.dto.DTOConverter;
import net.sam_solutions.courses.dkrauchanka.dto.UserDTO;
import net.sam_solutions.courses.dkrauchanka.utils.Password;

public class UserService {
    private UserDAOImpl userDao = new UserDAOImpl();
    
    public void addNewUser(String login, String fn, String ln){
        RoleDAOImpl roleDao = new RoleDAOImpl();
        Role role = roleDao.getRole("user");
        User user = new User(login,Password.hashPassword(Password.hashPassword(login)+Password.SALT),fn,ln,role);
        userDao.addUser(user);
    }
    
    public Integer getPagesCount(int onPage){
        Long count = userDao.countUser();
        return (int)Math.ceil((double)count/onPage);
    }
    
    public Integer getCount(){
        return (int)Math.ceil(userDao.countUser());
    }
    
    public UserDTO getUser(String login){
        User user = userDao.getUser(login);
        if(user != null)
            return new UserDTO(user);
        else
            return null;
    }
    
    public void removeUser(String login){
        userDao.removeUser(new User(login,null,null,null,null));
    }
    
    public List<UserDTO> listUsers(int page, int count){
        List<User> list = userDao.listUser(page,count);
        List<UserDTO> listDto = DTOConverter.userToUserDTOList(list);
        return listDto;
    }
    
    public void changePass(String login, String pass){
        User user = userDao.getUser(login);
        user.setPass(Password.hashPassword(Password.hashPassword(pass)+Password.SALT));
        userDao.addUser(user);
    }
    
    public boolean userValidate(String login, String pass){
        User user = userDao.getUser(login);
        return user.getPass().equals(Password.hashPassword(Password.hashPassword(pass)+Password.SALT));
    }
}
