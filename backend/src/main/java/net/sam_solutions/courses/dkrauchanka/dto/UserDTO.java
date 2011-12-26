package net.sam_solutions.courses.dkrauchanka.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.sam_solutions.courses.dkrauchanka.domain.User;
import org.apache.wicket.IClusterable;

public class UserDTO implements IClusterable{
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
    
    public UserDTO(){
        
    }

    public UserDTO(String login, String firstName, String lastName) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserDTO other = (UserDTO) obj;
        if ((this.login == null) ? (other.login != null) : !this.login.equals(other.login)) {
            return false;
        }
        if ((this.firstName == null) ? (other.firstName != null) : !this.firstName.equals(other.firstName)) {
            return false;
        }
        if ((this.lastName == null) ? (other.lastName != null) : !this.lastName.equals(other.lastName)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + (this.login != null ? this.login.hashCode() : 0);
        hash = 13 * hash + (this.firstName != null ? this.firstName.hashCode() : 0);
        hash = 13 * hash + (this.lastName != null ? this.lastName.hashCode() : 0);
        return hash;
    }
    
    
}
