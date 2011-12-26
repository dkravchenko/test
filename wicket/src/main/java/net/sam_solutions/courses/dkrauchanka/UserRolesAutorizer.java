package net.sam_solutions.courses.dkrauchanka;

import net.sam_solutions.courses.dkrauchanka.view.HomePackage.Home;
import net.sam_solutions.courses.dkrauchanka.view.SignInPackage.SignIn;
import org.apache.wicket.Component;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.authroles.authorization.strategies.role.IRoleCheckingStrategy;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.component.IRequestableComponent;

public class UserRolesAutorizer implements IRoleCheckingStrategy, IAuthorizationStrategy{
    public UserRolesAutorizer()
    {
    }
    
    public boolean isActionAuthorized(Component component, Action action)
    {
        return true;
    }

    public <T extends IRequestableComponent> boolean isInstantiationAuthorized(
                Class<T> componentClass)
    {
        if (Home.class.isAssignableFrom(componentClass))
        {
             if (((SignInSession)Session.get()).isSignedIn())
             {
                 return true;
             }
             throw new RestartResponseAtInterceptPageException(SignIn.class);
        }
        return true;
    }
            
    public boolean hasAnyRole(Roles roles)
    {
        SignInSession authSession = (SignInSession)Session.get();
        if(authSession != null)
            return authSession.getUser().hasAnyRole(roles);
        else
            return false;
    }
}
