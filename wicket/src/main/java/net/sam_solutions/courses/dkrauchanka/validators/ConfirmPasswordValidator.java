package net.sam_solutions.courses.dkrauchanka.validators;

import net.sam_solutions.courses.dkrauchanka.NewPass;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.AbstractValidator;

public class ConfirmPasswordValidator extends AbstractValidator{
    private NewPass newPass;

    public ConfirmPasswordValidator(NewPass pass){
        this.newPass = pass;
    }
    
    @Override
    protected void onValidate(IValidatable iv) {
        String value = (String) iv.getValue();
        if(!value.equals(newPass.getNewPass())){
            error(iv);
        }
    }
    
    @Override
    protected String resourceKey() {
        return "error.missmatch";
    }
}
