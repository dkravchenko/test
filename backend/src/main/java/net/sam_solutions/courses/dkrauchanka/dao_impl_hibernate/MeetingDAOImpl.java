package net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate;

import java.util.List;

import net.sam_solutions.courses.dkrauchanka.dao.MeetingDAO;
import net.sam_solutions.courses.dkrauchanka.domain.Meeting;
import net.sam_solutions.courses.dkrauchanka.utils.HibernateUtil;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MeetingDAOImpl implements MeetingDAO{

	private SessionFactory sessionFactory;
	
	public MeetingDAOImpl(){
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	public void addMeeting(Meeting meeting) {
		Session session = sessionFactory.openSession();
		session.saveOrUpdate(meeting);
		session.close();
	}

	public List<Meeting> listMeeting() {
		Session session = sessionFactory.openSession();
		List<Meeting> list = session.createQuery("from Meeting").list();
		session.close();
		return list;
	}
	
	public Meeting getMeeting(Integer id){
		Session session = sessionFactory.openSession();
		Meeting meeting = (Meeting)session.get(Meeting.class,new Integer(id));
		session.close();
		return meeting;
	}

	public void removeMeeting(Meeting meeting) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(meeting);
		transaction.commit();
		session.close();
	}
}