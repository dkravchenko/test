package net.sam_solutions.courses.dkrauchanka.dao;

import java.util.List;

import net.sam_solutions.courses.dkrauchanka.domain.User;

public interface UserDAO {
	public void addUser(User user);
	public List<User> listUser(int page,int count);
	public void removeUser(User user);
	public User getUser(String login);
}
