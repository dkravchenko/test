package net.sam_solutions.courses.dkrauchanka.providers;

import java.util.Iterator;
import net.sam_solutions.courses.dkrauchanka.detachable_models.DetachableUserModel;
import net.sam_solutions.courses.dkrauchanka.dto.UserDTO;
import net.sam_solutions.courses.dkrauchanka.service.UserService;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

public class UserDataProvider extends SortableDataProvider<UserDTO>{
    private UserService userService = new UserService();

    public Iterator<UserDTO> iterator(int first, int count) {
        Integer page = (int)Math.ceil(first/10)+1;
        return userService.listUsers(page, 10).iterator();
    }

    public int size() {
        return userService.getCount()-1;
    }

    public IModel<UserDTO> model(UserDTO object) {
        return new DetachableUserModel(object);
    }

    public void detach() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
