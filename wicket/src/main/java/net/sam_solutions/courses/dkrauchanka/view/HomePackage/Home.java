package net.sam_solutions.courses.dkrauchanka.view.HomePackage;

import net.sam_solutions.courses.dkrauchanka.SignInSession;
import net.sam_solutions.courses.dkrauchanka.service.UserService;
import net.sam_solutions.courses.dkrauchanka.view.BasePagePackage.BasePage;
import net.sam_solutions.courses.dkrauchanka.view.ChangePassPackage.ChangePass;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

@AuthorizeInstantiation({"user","admin"})
public class Home extends BasePage{
    
    public Home(){
        super();
        UserService userService = new UserService();
        String login = ((SignInSession)this.getSession()).getLogin();
        if(userService.userValidate(login,login)){
            this.setResponsePage(ChangePass.class);
        }
    }
}
