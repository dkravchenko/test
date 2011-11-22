package net.sam_solutions.courses.dkrauchanka.framework;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	public String perform(HttpServletRequest request, HttpServletResponse response);
}
