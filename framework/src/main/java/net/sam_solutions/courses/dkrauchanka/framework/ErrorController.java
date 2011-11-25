package net.sam_solutions.courses.dkrauchanka.framework;

import javax.servlet.http.HttpServletRequest;

public class ErrorController implements Action {
	private Integer errorCode;
	private String errorText;
	private StackTraceElement[] trace;
	
	public ErrorController(Integer errorCode,String errorText,StackTraceElement[] trace){
		this.errorCode = errorCode;
		this.errorText = errorText;
		this.trace = trace;
	}
	public String perform(HttpServletRequest request) {
		request.setAttribute("errorCode", this.errorCode);
		request.setAttribute("errorText", this.errorText);
		request.setAttribute("stackTrace", this.trace);
		return "/WEB-INF/jsp/error.jsp";
	}

}
