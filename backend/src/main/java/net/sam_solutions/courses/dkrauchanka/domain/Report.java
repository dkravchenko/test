package net.sam_solutions.courses.dkrauchanka.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="reports")
public class Report {
    private Integer id;
    private Date dateOfReport;
    private Integer worgingHours;
    private Integer doneHours;
    private User user;
    private List<Task> tasks;

    public Report() {
    }

    
    public Report(Integer id, Date dateOfReport, Integer worgingHours, Integer doneHours, User user) {
        this.id = id;
        this.dateOfReport = dateOfReport;
        this.worgingHours = worgingHours;
        this.doneHours = doneHours;
        this.user = user;
    }
    
    @Id
    @GeneratedValue
    @Column(name="reports_id_reports")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="reports_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getDateOfReport() {
        return dateOfReport;
    }

    public void setDateOfReport(Date dateOfReport) {
        this.dateOfReport = dateOfReport;
    }
    
    @Column(name="reports_working_hours")
    public Integer getWorgingHours() {
        return worgingHours;
    }

    public void setWorgingHours(Integer worgingHours) {
        this.worgingHours = worgingHours;
    }

    @Column(name="reports_done_hours")
    public Integer getDoneHours() {
        return doneHours;
    }

    public void setDoneHours(Integer doneHours) {
        this.doneHours = doneHours;
    }
  
    @ManyToOne
    @JoinColumn(name = "reports_users_login", referencedColumnName = "users_login")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "reports_has_tasks", joinColumns = { @JoinColumn(name = "treports_id_reports") }, inverseJoinColumns = { @JoinColumn(name = "rtasks_id_task") })
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Report other = (Report) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (!sdf.format(this.dateOfReport).equals(sdf.format(other.getDateOfReport()))) {
            return false;
        }
        if (this.worgingHours != other.worgingHours && (this.worgingHours == null || !this.worgingHours.equals(other.worgingHours))) {
            return false;
        }
        if (this.doneHours != other.doneHours && (this.doneHours == null || !this.doneHours.equals(other.doneHours))) {
            return false;
        }
        if (this.user != other.user && (this.user == null || !this.user.equals(other.user))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 71 * hash + (this.dateOfReport != null ? this.dateOfReport.hashCode() : 0);
        hash = 71 * hash + (this.worgingHours != null ? this.worgingHours.hashCode() : 0);
        hash = 71 * hash + (this.doneHours != null ? this.doneHours.hashCode() : 0);
        hash = 71 * hash + (this.user != null ? this.user.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Report{" + "id=" + id + ", dateOfReport=" + dateOfReport + ", worgingHours=" + worgingHours + ", doneHours=" + doneHours + ", user=" + user + '}';
    }
    
    
}
