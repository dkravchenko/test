package net.sam_solutions.courses.dkrauchanka.view.TaskRulePackage;

import net.sam_solutions.courses.dkrauchanka.view.BasePagePackage.BasePage;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

@AuthorizeInstantiation("admin")
public class TaskRule extends BasePage{
    
}
