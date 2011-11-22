package net.sam_solutions.courses.dkrauchanka.framework;

import net.sam_solutions.courses.dkrauchanka.framework.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorAction404 implements Action{
	public String perform(HttpServletRequest request, HttpServletResponse response){
		return "/WEB-INF/404.jsp";
	}
}
