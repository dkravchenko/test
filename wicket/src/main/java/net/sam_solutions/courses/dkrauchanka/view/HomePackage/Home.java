package net.sam_solutions.courses.dkrauchanka.view.HomePackage;

import net.sam_solutions.courses.dkrauchanka.view.BasePagePackage.BasePage;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;

@AuthorizeInstantiation({"user","admin"})
public class Home extends BasePage{
    
    public Home(){
        super();
    }
}
