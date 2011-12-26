package net.sam_solutions.courses.dkrauchanka.view.BasePagePackage;

import net.sam_solutions.courses.dkrauchanka.view.LogoutPackage.Logout;
import net.sam_solutions.courses.dkrauchanka.view.ReportAddPackage.ReportAdd;
import net.sam_solutions.courses.dkrauchanka.view.ReportListPackage.ReportList;
import net.sam_solutions.courses.dkrauchanka.view.ReportSelfPackage.ReportSelf;
import net.sam_solutions.courses.dkrauchanka.view.TaskListPackage.TaskList;
import net.sam_solutions.courses.dkrauchanka.view.TaskRulePackage.TaskRule;
import net.sam_solutions.courses.dkrauchanka.view.TaskSelfPackage.TaskSelf;
import net.sam_solutions.courses.dkrauchanka.view.UserRulePackage.UserRule;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

public class BasePage extends WebPage{
    @AuthorizeAction(action = Action.RENDER, roles = "admin")
    private static final class AdminMenu extends Panel
    {
        public AdminMenu(String id)
        {
            super(id);
            add(new BookmarkablePageLink("page1Link", TaskRule.class));
            add(new BookmarkablePageLink("page2Link", ReportList.class));
            add(new BookmarkablePageLink("page3Link", UserRule.class));
        }
    }
    
    @AuthorizeAction(action = Action.RENDER, roles = "user")
    private static final class UserMenu extends Panel
    {
        public UserMenu(String id)
        {
            super(id);
            add(new BookmarkablePageLink("page5Link", TaskList.class));
            add(new BookmarkablePageLink("page6Link", TaskSelf.class));
            add(new BookmarkablePageLink("page7Link", ReportAdd.class));
            add(new BookmarkablePageLink("page8Link", ReportSelf.class));
        }
    }
    
    private static final class AllMenu extends Panel{
        public AllMenu(String id){
            super(id);
            add(new BookmarkablePageLink("page4Link", Logout.class));
        }
    }
    
    public BasePage(){
        add(new AdminMenu("adminMenu"));
        add(new UserMenu("userMenu"));
        add(new AllMenu("allMenu"));
    }
}
