package net.sam_solutions.courses.dkrauchanka;

import org.apache.wicket.IClusterable;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;

public class SessionUser implements IClusterable{
    private final String name;
    private final Roles role;
    
    public SessionUser(String uid, String roles)
    {
        if (uid == null)
        {
            throw new IllegalArgumentException("name must be not null");
        }
        if (roles == null)
        {
            throw new IllegalArgumentException("roles must be not null");
        }
        this.name = uid;
        this.role = new Roles();
        this.role.add(roles);
    }
    
    public boolean hasRole(String role)
    {
        return this.role.hasRole(role);
    }
    
    public boolean hasAnyRole(Roles roles)
    {
        return this.role.hasAnyRole(roles);
    }
    
    public String getName()
    {
        return name;
    }
    
    @Override
    public String toString()
    {
        return name + " " + role;
    }

    public Roles getRole() {
        return role;
    }
    
    
}
