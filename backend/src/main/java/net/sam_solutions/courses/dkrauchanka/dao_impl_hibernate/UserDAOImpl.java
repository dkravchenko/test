package net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate;

import java.util.List;

import net.sam_solutions.courses.dkrauchanka.dao.UserDAO;
import net.sam_solutions.courses.dkrauchanka.domain.Role;
import net.sam_solutions.courses.dkrauchanka.domain.User;
import net.sam_solutions.courses.dkrauchanka.utils.HibernateUtil;

import org.hibernate.Query;
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

	public List<User> listUser(int page, int count) {
		Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
                RoleDAOImpl roleDao = new RoleDAOImpl();
                Role role = roleDao.getRole("user");
                Query query = (Query)session.createQuery("from User where role=:role").setParameter("role", role);
                query.setFirstResult((page-1)*count);
                query.setMaxResults(count);
		List<User> list = query.list();
                transaction.commit();
		session.close();
		return list;
	}
        
        public Long countUser(){
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("select count(*) from User");
            Long temp = (Long) query.uniqueResult();
            transaction.commit();
            session.close();
            return temp;
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
