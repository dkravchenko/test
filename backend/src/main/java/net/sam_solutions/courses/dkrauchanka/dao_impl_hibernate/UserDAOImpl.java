package net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate;

import java.util.List;

import net.sam_solutions.courses.dkrauchanka.dao.UserDAO;
import net.sam_solutions.courses.dkrauchanka.domain.User;
import net.sam_solutions.courses.dkrauchanka.utils.HibernateUtil;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDAOImpl implements UserDAO{

	private SessionFactory sessionFactory;
	
	public UserDAOImpl(){
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	public void addUser(User user) {
		Session session = sessionFactory.openSession();
		session.save(user);
		session.close();
	}

	public List<User> listUser() {
		Session session = sessionFactory.openSession();
		List<User> list = session.createQuery("from User").list();
		session.close();
		return list;
	}
	
	public User getUser(Integer id){
		Session session = sessionFactory.openSession();
		User user = (User)session.get(User.class,new Integer(id));
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
	
	public void updateUser(User user){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(user);
		transaction.commit();
		session.close();
	}
}
