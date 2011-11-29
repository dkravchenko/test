package net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate;

import java.util.List;

import net.sam_solutions.courses.dkrauchanka.dao.TaskDAO;
import net.sam_solutions.courses.dkrauchanka.domain.Task;
import net.sam_solutions.courses.dkrauchanka.utils.HibernateUtil;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TaskDAOImpl implements TaskDAO{
	
	private SessionFactory sessionFactory;
	
	public TaskDAOImpl(){
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	public void addTask(Task task) {
		Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(task);
                transaction.commit();
		session.close();
	}

	public List<Task> listTask() {
		Session session = sessionFactory.openSession();
		List<Task> list = session.createQuery("from Task").list();
		session.close();
		return list;
	}
	
	public Task getTask(Integer id){
		Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
		Task task = (Task)session.get(Task.class,new Integer(id));
                transaction.commit();
		session.close();
		return task;
	}

	public void removeTask(Task task) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(task);
		transaction.commit();
		session.close();
	}
}
