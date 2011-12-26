package net.sam_solutions.courses.dkrauchanka.domain;

import java.util.ArrayList;
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
@Table(name = "tasks")
public class Task {
	private Integer id;
	private String title;
	private String text;
        private Integer hoursToDo;
        private String status;
        private Date endTime;
	private User user;
        private List<Report> reports;
	
	public Task(){
		
	}
	
	public Task(Integer id, String title, String text, Integer hoursToDo, String status, Date endTime, User user){
		this.id = id;
		this.title = title;
		this.text = text;
		this.hoursToDo = hoursToDo;
                this.status = status;
                this.endTime = endTime;
                this.user = user;        
	}
	
	
	@Id
	@GeneratedValue
	@Column(name = "tasks_id_task")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "tasks_title", nullable = false, length = 300)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "tasks_text")
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	@Column(name = "tasks_hours_todo")
	public Integer getHoursToDo() {
		return hoursToDo;
	}
	public void setHoursToDo(Integer hoursToDo) {
		this.hoursToDo = hoursToDo;
	}
        
        @Column(name = "tasks_status")
        public String getStatus(){
            return status;
        }
        public void setStatus(String status){
            this.status = status;
        }
        
        @Column(name = "tasks_end_time")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date date) {
		this.endTime = date;
	}
	
	@JoinColumn(name = "tasks_users_login", referencedColumnName = "users_login")
	@ManyToOne
	public User getUser(){
		return this.user;
	}
	
	public void setUser(User user){
		this.user = user;
	}

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "reports_has_tasks", joinColumns = { @JoinColumn(name = "rtasks_id_task") }, inverseJoinColumns = { @JoinColumn(name = "treports_id_reports") })
        public List<Report> getReports() {
            return reports;
        }

        public void setReports(List<Report> reports) {
           this.reports = reports;
        }
        
        public void addReportToTask(Report report){
            if(this.reports == null)
                this.reports = new ArrayList<Report>();
            this.reports.add(report);
        }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Task other = (Task) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.title == null) ? (other.title != null) : !this.title.equals(other.title)) {
            return false;
        }
        if ((this.text == null) ? (other.text != null) : !this.text.equals(other.text)) {
            return false;
        }
        if (this.hoursToDo != other.hoursToDo && (this.hoursToDo == null || !this.hoursToDo.equals(other.hoursToDo))) {
            return false;
        }
        if ((this.status == null) ? (other.status != null) : !this.status.equals(other.status)) {
            return false;
        }
        if (this.user != other.user && (this.user == null || !this.user.equals(other.user))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 17 * hash + (this.title != null ? this.title.hashCode() : 0);
        hash = 17 * hash + (this.text != null ? this.text.hashCode() : 0);
        hash = 17 * hash + (this.hoursToDo != null ? this.hoursToDo.hashCode() : 0);
        hash = 17 * hash + (this.status != null ? this.status.hashCode() : 0);
        hash = 17 * hash + (this.user != null ? this.user.hashCode() : 0);
        return hash;
    }

    

    
    
}
