package net.sam_solutions.courses.dkrauchanka.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import net.sam_solutions.courses.dkrauchanka.domain.Report;
import net.sam_solutions.courses.dkrauchanka.domain.Task;

public class ReportDTO {
    private Integer id;
    private Date dateOfReport;
    private Integer workingHours;
    private Integer doneHours;
    private String user;
    private String tasks;

    public ReportDTO(Integer id, Date dateOfReport, Integer workingHours, Integer doneHours, String user, String tasks) {
        this.id = id;
        this.dateOfReport = dateOfReport;
        this.workingHours = workingHours;
        this.doneHours = doneHours;
        this.user = user;
        this.tasks = tasks;
    }

    public ReportDTO(Report report){
        this.id = report.getId();
        this.dateOfReport = report.getDateOfReport();
        this.workingHours = report.getWorgingHours();
        this.doneHours = report.getDoneHours();
        this.user = report.getUser().getFirstName()+" "+report.getUser().getLastName();
        List<Task> tasks = report.getTasks();
        String taskStr = "";
        Iterator<Task> iterTask = tasks.iterator();
        while(iterTask.hasNext()){
             Task task = iterTask.next();
             taskStr += task.getTitle() + ", ";
        }
        if(tasks.size() >= 1)
             taskStr.substring(0, taskStr.lastIndexOf(",")-1);
        this.tasks = taskStr;
    }
    
    public static List<ReportDTO> reportListToReportDTOList(List<Report> list){
        if(list != null){
            List<ReportDTO> temp = new ArrayList<ReportDTO>();
            Iterator<Report> iter = list.iterator();
            while(iter.hasNext()){
                Report report = iter.next();
                List<Task> tasks = report.getTasks();
                String taskStr = "";
                Iterator<Task> iterTask = tasks.iterator();
                while(iterTask.hasNext()){
                    Task task = iterTask.next();
                    taskStr += task.getTitle() + ", ";
                }
                if(tasks.size() >= 1)
                    taskStr.substring(0, taskStr.lastIndexOf(","));
                ReportDTO reportDto = new ReportDTO(report.getId(),report.getDateOfReport(),report.getWorgingHours(),report.getDoneHours(),report.getUser().getFirstName()+" "+report.getUser().getLastName(),taskStr);
                temp.add(reportDto);
            }
            return temp;
        }
        else{
            return null;
        }
    }
    public Date getDateOfReport() {
        return dateOfReport;
    }

    public void setDateOfReport(Date dateOfReport) {
        this.dateOfReport = dateOfReport;
    }

    public Integer getDoneHours() {
        return doneHours;
    }

    public void setDoneHours(Integer doneHours) {
        this.doneHours = doneHours;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTasks() {
        return tasks;
    }

    public void setTasks(String tasks) {
        this.tasks = tasks;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Integer workingHours) {
        this.workingHours = workingHours;
    }
    
    
}
