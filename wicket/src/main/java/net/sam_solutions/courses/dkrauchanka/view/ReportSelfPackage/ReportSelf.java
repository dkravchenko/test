package net.sam_solutions.courses.dkrauchanka.view.ReportSelfPackage;

import net.sam_solutions.courses.dkrauchanka.view.BasePagePackage.BasePage;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

@AuthorizeInstantiation("user")
public class ReportSelf extends BasePage{
    
}
