package net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate;

import java.util.List;

import net.sam_solutions.courses.dkrauchanka.dao.UserDAO;
import net.sam_solutions.courses.dkrauchanka.domain.User;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO{
	@Autowired
	private SessionFactory sessionFactory;
	
	public void addUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> listUser() {

		return sessionFactory.getCurrentSession().createQuery("from User")
			.list();
	}

	public void removeUser(Integer id) {
		User user = (User) sessionFactory.getCurrentSession().load(
				User.class, id);
		if (null != user) {
			sessionFactory.getCurrentSession().delete(user);
		}

	}
}
