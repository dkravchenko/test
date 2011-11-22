package net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate;

import java.util.List;

import net.sam_solutions.courses.dkrauchanka.dao.MeetingDAO;
import net.sam_solutions.courses.dkrauchanka.domain.Meeting;

import org.hibernate.SessionFactory;


public class MeetingDAOImpl implements MeetingDAO{
	
	private SessionFactory sessionFactory;
	
	public void addMeeting(Meeting meeting) {
		sessionFactory.getCurrentSession().save(meeting);
	}

	public List<Meeting> listMeeting() {

		return sessionFactory.getCurrentSession().createQuery("from Meeting")
			.list();
	}

	public void removeMeeting(Integer id) {
		Meeting meeting = (Meeting) sessionFactory.getCurrentSession().load(
				Meeting.class, id);
		if (null != meeting) {
			sessionFactory.getCurrentSession().delete(meeting);
		}

	}

}
