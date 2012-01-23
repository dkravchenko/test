package net.sam_solutions.courses.dkrauchanka.dto;

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
        if(tasks != null) {
            Iterator<Task> iterTask = tasks.iterator();
            while(iterTask.hasNext()){
                Task task = iterTask.next();
                taskStr += task.getTitle() + ", ";
            }
            if(tasks.size() >= 1)
                taskStr.substring(0, taskStr.lastIndexOf(",")-1);
            this.tasks = taskStr;
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

    @Override
    public String toString() {
        return "ReportDTO{" + "id=" + id + ", dateOfReport=" + dateOfReport + ", workingHours=" + workingHours + ", doneHours=" + doneHours + ", user=" + user + ", tasks=" + tasks + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReportDTO other = (ReportDTO) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 61 * hash + (this.dateOfReport != null ? this.dateOfReport.hashCode() : 0);
        hash = 61 * hash + (this.workingHours != null ? this.workingHours.hashCode() : 0);
        hash = 61 * hash + (this.doneHours != null ? this.doneHours.hashCode() : 0);
        hash = 61 * hash + (this.user != null ? this.user.hashCode() : 0);
        hash = 61 * hash + (this.tasks != null ? this.tasks.hashCode() : 0);
        return hash;
    }
    
    
    
}
