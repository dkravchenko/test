package net.sam_solutions.courses.dkrauchanka;

import net.sam_solutions.courses.dkrauchanka.view.HomePackage.Home;
import net.sam_solutions.courses.dkrauchanka.view.SignInPackage.SignIn;
import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.IAuthorizationStrategy;
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
    }
}
