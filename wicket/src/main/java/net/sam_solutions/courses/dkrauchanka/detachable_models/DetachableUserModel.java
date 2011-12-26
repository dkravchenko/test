package net.sam_solutions.courses.dkrauchanka.detachable_models;

import net.sam_solutions.courses.dkrauchanka.dto.UserDTO;
import net.sam_solutions.courses.dkrauchanka.service.UserService;
import org.apache.wicket.model.LoadableDetachableModel;

public class DetachableUserModel extends LoadableDetachableModel<UserDTO>{
    private final String login;
    
    public DetachableUserModel(String login){
        if(login == null || login.equals("")){
            throw new IllegalArgumentException();
        }
        this.login = login;
    }
    
    public DetachableUserModel(UserDTO user){
        this(user.getLogin());
    }
    
    @Override
    protected UserDTO load() {
        UserService userService = new UserService();
        return userService.getUser(login);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DetachableUserModel other = (DetachableUserModel) obj;
        if ((this.login == null) ? (other.login != null) : !this.login.equals(other.login)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + (this.login != null ? this.login.hashCode() : 0);
        return hash;
    }
    
    
}
