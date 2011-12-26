package net.sam_solutions.courses.dkrauchanka.dao;

import java.util.List;

import net.sam_solutions.courses.dkrauchanka.domain.Role;

public interface RoleDAO {
	public void addRole(Role role);
	public List<Role> listRole();
	public void removeRole(Role role);
	public Role getRole(String role);
}
