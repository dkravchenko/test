package net.sam_solutions.courses.dkrauchanka.dao;

import java.util.List;

import net.sam_solutions.courses.dkrauchanka.domain.Meeting;

public interface MeetingDAO {
	public void addMeeting(Meeting meeting);
	public List<Meeting> listMeeting();
	public void removeMeeting(Meeting meeting);
	public Meeting getMeeting(Integer id);
}
