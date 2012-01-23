package net.sam_solutions.courses.dkrauchanka;

import org.apache.wicket.request.Request;
import net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate.UserDAOImpl;
import net.sam_solutions.courses.dkrauchanka.domain.User;
import net.sam_solutions.courses.dkrauchanka.utils.Password;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;

public final class SignInSession extends AuthenticatedWebSession{
    private SessionUser user;
    
    protected SignInSession(Request request)
    {
        super(request);
    }
    
    public final boolean authenticate(final String username, final String password)
    {
        if (user == null)
        {
            UserDAOImpl userDao = new UserDAOImpl();
            User us = userDao.getUser(username);
            if (us != null && us.getPass().equals(Password.hashPassword(Password.hashPassword(password)+Password.SALT)))
            {
                user = new SessionUser(us.getLogin(),us.getRole().getRole());
            }
        }

        return user != null;
    }

    @Override
    public Roles getRoles()
    {
        if(user == null)
            return null;
        return user.getRole();
    }

    public SessionUser getUser() {
        return user;
    }
    
    public String getLogin(){
        return user.getName();
    }
    
}
