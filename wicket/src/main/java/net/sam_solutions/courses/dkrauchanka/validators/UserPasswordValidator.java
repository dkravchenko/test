package net.sam_solutions.courses.dkrauchanka.validators;

import java.util.Map;
import net.sam_solutions.courses.dkrauchanka.service.UserService;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.AbstractValidator;

public class UserPasswordValidator extends AbstractValidator{
    private String login;
    private UserService userService = new UserService();
    
    public UserPasswordValidator(String login){
        this.login = login;

    }
    
    @Override
    protected void onValidate(IValidatable iv) {
        String value = (String) iv.getValue();
        if(!userService.userValidate(login,value)){
            error(iv);
        }
    }
    
    @Override
    protected String resourceKey() {
        return "error.invalidpass";
    }
}
