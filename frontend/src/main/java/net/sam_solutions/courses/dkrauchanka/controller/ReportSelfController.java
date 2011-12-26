package net.sam_solutions.courses.dkrauchanka.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.sam_solutions.courses.dkrauchanka.dto.ReportDTO;
import net.sam_solutions.courses.dkrauchanka.framework.Action;
import net.sam_solutions.courses.dkrauchanka.service.ReportService;

public class ReportSelfController implements Action{
    private Integer number = 10;

    @Override
    public String perform(HttpServletRequest request) {
        ReportService reportService = new ReportService();
        Integer page = 1;
        if(request.getParameter("page") != null && !request.getParameter("page").equals("")){
            page = Integer.valueOf(request.getParameter("page"));
        }
        List<ReportDTO> list = reportService.listReportByUser(page, number, (String)request.getSession().getAttribute("login"));
        request.setAttribute("pages",reportService.getPagesCountByUser(number, (String)request.getSession().getAttribute("login")));
        request.setAttribute("reportList", list);
        request.setAttribute("currentPage", page);
        return "/WEB-INF/jsp/reportself.jsp";
    }
    
}
