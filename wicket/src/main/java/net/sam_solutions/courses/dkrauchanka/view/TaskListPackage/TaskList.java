package net.sam_solutions.courses.dkrauchanka.view.TaskListPackage;

import net.sam_solutions.courses.dkrauchanka.view.BasePagePackage.BasePage;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

@AuthorizeInstantiation("user")
public class TaskList extends BasePage{
    
}
