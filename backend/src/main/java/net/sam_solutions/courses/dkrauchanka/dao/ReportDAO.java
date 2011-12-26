package net.sam_solutions.courses.dkrauchanka.dao;

import java.util.List;

import net.sam_solutions.courses.dkrauchanka.domain.Report;

public interface ReportDAO {
	public void addReport(Report report);
	public List<Report> listReport(int page, int count);
	public void removeReport(Report report);
	public Report getReport(Integer id);
}
