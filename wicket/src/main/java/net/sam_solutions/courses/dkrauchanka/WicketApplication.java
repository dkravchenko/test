package net.sam_solutions.courses.dkrauchanka;

import net.sam_solutions.courses.dkrauchanka.view.ChangePassPackage.ChangePass;
import net.sam_solutions.courses.dkrauchanka.view.Error404Page.Error404Page;
import net.sam_solutions.courses.dkrauchanka.view.HomePackage.Home;
import net.sam_solutions.courses.dkrauchanka.view.LogoutPackage.Logout;
import net.sam_solutions.courses.dkrauchanka.view.ReportAddPackage.ReportAdd;
import net.sam_solutions.courses.dkrauchanka.view.ReportListPackage.ReportList;
import net.sam_solutions.courses.dkrauchanka.view.ReportSelfPackage.ReportSelf;
import net.sam_solutions.courses.dkrauchanka.view.SignInPackage.SignIn;
import net.sam_solutions.courses.dkrauchanka.view.TaskListPackage.TaskList;
import net.sam_solutions.courses.dkrauchanka.view.TaskRulePackage.TaskRule;
import net.sam_solutions.courses.dkrauchanka.view.TaskSelfPackage.TaskSelf;
import net.sam_solutions.courses.dkrauchanka.view.UserRulePackage.UserRule;
import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

public class WicketApplication extends AuthenticatedWebApplication{

    @Override
    protected Class<? extends AuthenticatedWebSession> getWebSessionClass()
    {
        return SignInSession.class;
    }
    
    @Override
    public Class<? extends Page> getHomePage() {
        return Home.class;
    }
    
    @Override
    protected Class<? extends WebPage> getSignInPageClass()
    {
        return SignIn.class;
    }
    
     @Override
    public Session newSession(Request request, Response response)
    {
        return new SignInSession(request);
    }

    @Override
    protected void init()
    {
        super.init();
        getDebugSettings().setDevelopmentUtilitiesEnabled(true);
        mountPackage("admin", ReportList.class);
        mountPackage("admin", TaskRule.class);
        mountPackage("admin", UserRule.class);
        mountPackage("all", Logout.class);
        mountPackage("all", Error404Page.class);
        mountPackage("all", SignIn.class);
        mountPackage("all", Home.class);
        mountPackage("all", ChangePass.class);
        mountPackage("user", ReportAdd.class);
        mountPackage("user", ReportSelf.class);
        mountPackage("user", TaskList.class);
        mountPackage("user", TaskSelf.class);
    }
}
