package net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate;

import junit.framework.*;

import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.Date;
import java.util.Iterator;
import org.apache.log4j.Logger;

import net.sam_solutions.courses.dkrauchanka.domain.User;
import net.sam_solutions.courses.dkrauchanka.domain.Meeting;


public class MeetingDAOImplTest extends TestCase {
	public static final Logger logger = Logger.getLogger(MeetingDAOImplTest.class);	
	public MeetingDAOImplTest(String str){
		super(str);
	}
	
	public void testAddingMeeting(){
		MeetingDAOImpl meetingDao = new MeetingDAOImpl();
		UserDAOImpl userDao = new UserDAOImpl();
		User user = userDao.getUser(new Integer(1));
		Meeting meeting = new Meeting(null,"asd","sad",new Date(),user);
		meetingDao.addMeeting(meeting);
		Integer id = meeting.getId();
		Meeting newMeeting = meetingDao.getMeeting(id);
		assertEquals(meeting.getUser(),newMeeting.getUser());
		meetingDao.removeMeeting(meeting);
	}
	
	public void testRemovingMeeting(){
		MeetingDAOImpl meetingDao = new MeetingDAOImpl();
		UserDAOImpl userDao = new UserDAOImpl();
		User user = userDao.getUser(new Integer(1));
		Meeting meeting = new Meeting(null,"asd","sad",new Date(),user);
		meetingDao.addMeeting(meeting);
		Integer id = meeting.getId();
		meetingDao.removeMeeting(meeting);
		Meeting newMeeting = meetingDao.getMeeting(id);
		assertNull(newMeeting);
	}
	
	public void testUpdatingMeeting(){
		MeetingDAOImpl meetingDao = new MeetingDAOImpl();
		UserDAOImpl userDao = new UserDAOImpl();
		User user = userDao.getUser(new Integer(1));
		Meeting meeting = new Meeting(null,"asd","sad",new Date(),user);
		meetingDao.addMeeting(meeting);
		Integer id = meeting.getId();
		meeting.setTitle("aaa");
		meetingDao.updateMeeting(meeting);
		Meeting newMeeting = meetingDao.getMeeting(id);
		assertEquals(newMeeting.getTitle(),meeting.getTitle());
		meetingDao.removeMeeting(meeting);
	}
}
