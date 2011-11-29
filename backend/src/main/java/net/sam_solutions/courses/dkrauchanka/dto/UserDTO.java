package net.sam_solutions.courses.dkrauchanka.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.sam_solutions.courses.dkrauchanka.domain.User;

public class UserDTO {
    private String login;
    private String firstName;
    private String lastName;
    
    public static List<UserDTO> userToUserDTOList(List<User> list){
        try{
            List<UserDTO> temp = new ArrayList<UserDTO>();
            Iterator<User> iter = list.iterator();
            while(iter.hasNext()){
                UserDTO userDto = new UserDTO(iter.next());
                temp.add(userDto);
            }
            return temp;
        }
        catch(NullPointerException e){
            return null;
        }
    }
    
    public UserDTO(User user){
        this.login = user.getLogin();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
