package net.sam_solutions.courses.dkrauchanka.dao;

import java.util.List;

import net.sam_solutions.courses.dkrauchanka.domain.Task;

public interface TaskDAO {
	public void addTask(Task task);
	public List<Task> listTask();
	public void removeTask(Integer id);
}
