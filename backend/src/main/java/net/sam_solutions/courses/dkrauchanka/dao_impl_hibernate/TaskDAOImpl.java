package net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate;

import java.util.List;

import net.sam_solutions.courses.dkrauchanka.dao.TaskDAO;
import net.sam_solutions.courses.dkrauchanka.domain.Task;

import org.hibernate.SessionFactory;


public class TaskDAOImpl implements TaskDAO{
	
	private SessionFactory sessionFactory;
	
	public void addTask(Task task) {
		sessionFactory.getCurrentSession().save(task);
	}

	public List<Task> listTask() {

		return sessionFactory.getCurrentSession().createQuery("from Task")
			.list();
	}

	public void removeTask(Integer id) {
		Task task = (Task) sessionFactory.getCurrentSession().load(
				Task.class, id);
		if (null != task) {
			sessionFactory.getCurrentSession().delete(task);
		}

	}
}
