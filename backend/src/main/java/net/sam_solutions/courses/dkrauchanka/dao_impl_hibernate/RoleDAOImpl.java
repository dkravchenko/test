package net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate;

import java.util.List;

import net.sam_solutions.courses.dkrauchanka.dao.RoleDAO;
import net.sam_solutions.courses.dkrauchanka.domain.Role;
import net.sam_solutions.courses.dkrauchanka.utils.HibernateUtil;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RoleDAOImpl implements RoleDAO{

	private SessionFactory sessionFactory;
	
	public RoleDAOImpl(){
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	public void addRole(Role role) {
		Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(role);
                transaction.commit();
		session.close();
	}

	public List<Role> listRole() {
		Session session = sessionFactory.openSession();
                //Transaction transaction = session.beginTransaction();
		List<Role> list = session.createQuery("from Role").list();
                //transaction.commit();
		session.close();
		return list;
	}
	
	public Role getRole(String rol){
		Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
		Role role = (Role)session.get(Role.class,rol);
                transaction.commit();
		session.close();
		return role;
	}

	public void removeRole(Role role) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(role);
		transaction.commit();
		session.close();
	}
}
