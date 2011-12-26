package net.sam_solutions.courses.dkrauchanka.view.LogoutPackage;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;

@AuthorizeInstantiation({"admin","user"})
public class Logout extends WebPage{
    public Logout(){
        getSession().invalidate();
    }
    
}
