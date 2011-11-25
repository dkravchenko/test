package net.sam_solutions.courses.dkrauchanka.controller;

import net.sam_solutions.courses.dkrauchanka.framework.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloController implements Action{
	public String perform(HttpServletRequest request){
		return "/WEB-INF/jsp/hello.jsp";
	}
}
