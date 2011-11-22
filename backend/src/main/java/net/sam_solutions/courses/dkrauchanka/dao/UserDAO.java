package net.sam_solutions.courses.dkrauchanka.dao;

import java.util.List;

import net.sam_solutions.courses.dkrauchanka.domain.User;

public interface UserDAO {
	public void addUser(User user);
	public List<User> listUser();
	public void removeUser(Integer id);
}
