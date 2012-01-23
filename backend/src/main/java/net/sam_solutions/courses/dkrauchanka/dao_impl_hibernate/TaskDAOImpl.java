package net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate;



import java.util.List;

import net.sam_solutions.courses.dkrauchanka.dao.TaskDAO;
import net.sam_solutions.courses.dkrauchanka.domain.Task;
import net.sam_solutions.courses.dkrauchanka.domain.User;
import net.sam_solutions.courses.dkrauchanka.utils.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
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

	public List<Task> listTask(int page, int count) {
		Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
		Query query = (Query)session.createQuery("from Task");
                query.setFirstResult((page-1)*count);
                query.setMaxResults(count);
                List<Task> list = query.list();
                transaction.commit();
		session.close();
		return list;
	}
        
        public List<Task> listTaskByUser(int page, int count, String login){
            Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
                UserDAOImpl userDao = new UserDAOImpl();
                User user = userDao.getUser(login);
		Query query = (Query)session.createQuery("from Task where user=:user").setParameter("user", user);
                query.setFirstResult((page-1)*count);
                query.setMaxResults(count);
                List<Task> list = query.list();
                transaction.commit();
		session.close();
		return list;
        }
        
        public List<Task> listUnclosedTaskByUser(int page, int count, String login){
            Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
                UserDAOImpl userDao = new UserDAOImpl();
                User user = userDao.getUser(login);
		Query query = (Query)session.createQuery("from Task where user=:user and not status=:status").setParameter("user", user).setString("status", "closed");
                query.setFirstResult((page-1)*count);
                query.setMaxResults(count);
                List<Task> list = query.list();
                transaction.commit();
		session.close();
		return list;
        }
        
        public List<Task> listTaskByUser(User user, int page, int count) {
                Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
                Query query = (Query)session.createQuery("from Task where User= :user").setParameter("user", user);
                query.setFirstResult((page-1)*count);
                query.setMaxResults(count);
		List<Task> list = query.list();
                transaction.commit();
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
        
        public Long countTask(){
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("select count(*) from Task");
            Long temp = (Long) query.uniqueResult();
            transaction.commit();
            session.close();
            return temp;
        }
        
        public Long countTaskByUser(String login){
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            UserDAOImpl userDao = new UserDAOImpl();
            User user = userDao.getUser(login);
            Query query = session.createQuery("select count(*) from Task where user=:user").setParameter("user", user);
            Long temp = (Long) query.uniqueResult();
            transaction.commit();
            session.close();
            return temp;
        }

	public void removeTask(Task task) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(task);
		transaction.commit();
		session.close();
	}
}
