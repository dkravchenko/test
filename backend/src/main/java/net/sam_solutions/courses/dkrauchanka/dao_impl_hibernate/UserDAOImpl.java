package net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate;

import java.util.Iterator;
import java.util.List;

import net.sam_solutions.courses.dkrauchanka.dao.UserDAO;
import net.sam_solutions.courses.dkrauchanka.domain.User;
import net.sam_solutions.courses.dkrauchanka.utils.HibernateUtil;

import net.sam_solutions.courses.dkrauchanka.utils.Password;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDAOImpl implements UserDAO{

	private SessionFactory sessionFactory;
	
	public UserDAOImpl(){
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	public void addUser(User user) {
		Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(user);
                transaction.commit();
		session.close();
	}

	public List<User> listUser() {
		Session session = sessionFactory.openSession();
                //Transaction transaction = session.beginTransaction();
		List<User> list = session.createQuery("from User").list();
                //transaction.commit();
		session.close();
		return list;
	}
	
	public User getUser(String login){
		Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
		User user = (User)session.get(User.class,login);
                transaction.commit();
		session.close();
		return user;
	}

	public void removeUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(user);
		transaction.commit();
		session.close();
	}
}
