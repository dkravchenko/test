package net.sam_solutions.courses.dkrauchanka.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate.ReportDAOImpl;
import net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate.TaskDAOImpl;
import net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate.UserDAOImpl;
import net.sam_solutions.courses.dkrauchanka.domain.Report;
import net.sam_solutions.courses.dkrauchanka.domain.Task;
import net.sam_solutions.courses.dkrauchanka.domain.User;
import net.sam_solutions.courses.dkrauchanka.dto.DTOConverter;
import net.sam_solutions.courses.dkrauchanka.dto.ReportDTO;

public class ReportService {
    private ReportDAOImpl reportDao = new ReportDAOImpl();
    
    public List<ReportDTO> listReport(int page, int count){
        List<Report> list = reportDao.listReport(page, count);
        return DTOConverter.reportListToReportDTOList(list);
    }
    
    public List<ReportDTO> listReportByUser(int page, int count, String login){
        List<Report> list = reportDao.listReportByUser(page, count, login);
        return DTOConverter.reportListToReportDTOList(list);
    }
    
    public Integer getPagesCount(int onPage){
        Long count = reportDao.countReport();
        return (int)Math.ceil((double)count/onPage);
    }
    
    public Integer getPagesCountByUser(int onPage, String login){
        Long count = reportDao.countReportByUser(login);
        return (int)Math.ceil((double)count/onPage);
    }
    
    public ReportDTO getReport(Integer id){
        return new ReportDTO(reportDao.getReport(id));
    }
    
    public void addReport(List<String> hidden, List<String> count,String workingHours, String login){
        if(hidden.size() == count.size()){
            TaskDAOImpl taskdao = new TaskDAOImpl();
            TaskService taskService = new TaskService();
            Integer totalHours = 0;
            List<Task> listTask = new ArrayList<Task>();
            Iterator<String> iterHidden = hidden.iterator();
            Iterator<String> iterCount = count.iterator();
            while(iterHidden.hasNext()){
                Task taskToEdit = taskdao.getTask(Integer.valueOf(iterHidden.next()));
                Integer hours = Integer.valueOf(iterCount.next());
                taskToEdit.setHoursToDo(taskToEdit.getHoursToDo() - hours);
                if(taskToEdit.getHoursToDo() < 0){
                    taskService.closeTask(taskToEdit.getId());
                }
                else{
                    taskdao.addTask(taskToEdit);
                }
                listTask.add(taskToEdit);
                totalHours += hours;
            }
            UserDAOImpl userDao = new UserDAOImpl();
            User user = userDao.getUser(login);
            Report report = new Report(null,new Date(),Integer.valueOf(workingHours),totalHours,user);
            report.setTasks(listTask);
            reportDao.addReport(report);
        }
    }
}
