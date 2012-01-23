package net.sam_solutions.courses.dkrauchanka.view.LogoutPackage;

import org.apache.wicket.authroles.authentication.pages.SignOutPage;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

@AuthorizeInstantiation({"admin","user"})
public class Logout extends SignOutPage{
    public Logout(){
        super();
        //throw new RestartResponseException(SignIn.class);
    }  
}
