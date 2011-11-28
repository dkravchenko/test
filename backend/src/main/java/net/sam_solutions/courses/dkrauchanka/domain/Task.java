package net.sam_solutions.courses.dkrauchanka.domain;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;


@Entity
@Table(name = "tasks")
public class Task {
	private Integer id;
	private String title;
	private String text;
	private String path;
        private String status;
        private Date endTime;
        private Integer closed;
	private User user;
	
	public Task(){
		
	}
	
	public Task(Integer id, String title, String text, String path, String status, Integer closed, User user){
		this.id = id;
		this.title = title;
		this.text = text;
		this.path = path;
                this.status = status;
                this.closed = closed;
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
	
	@Column(name = "tasks_path")
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
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
        
        @Column(name = "tasks_closed")
        public Integer getClosed(){
            return closed;
        }
        public void setClosed(Integer closed){
            this.closed = closed;
        }
	
	@JoinColumn(name = "tasks_users_login", referencedColumnName = "users_login")
	@ManyToOne
	public User getUser(){
		return this.user;
	}
	
	public void setUser(User user){
		this.user = user;
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
        if ((this.path == null) ? (other.path != null) : !this.path.equals(other.path)) {
            return false;
        }
        if ((this.status == null) ? (other.status != null) : !this.status.equals(other.status)) {
            return false;
        }
        if (this.endTime != other.endTime && (this.endTime == null || !this.endTime.equals(other.endTime))) {
            return false;
        }
        if (this.closed != other.closed && (this.closed == null || !this.closed.equals(other.closed))) {
            return false;
        }
        if (this.user != other.user && (this.user == null || !this.user.equals(other.user))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 83 * hash + (this.title != null ? this.title.hashCode() : 0);
        hash = 83 * hash + (this.text != null ? this.text.hashCode() : 0);
        hash = 83 * hash + (this.path != null ? this.path.hashCode() : 0);
        hash = 83 * hash + (this.status != null ? this.status.hashCode() : 0);
        hash = 83 * hash + (this.endTime != null ? this.endTime.hashCode() : 0);
        hash = 83 * hash + (this.closed != null ? this.closed.hashCode() : 0);
        hash = 83 * hash + (this.user != null ? this.user.hashCode() : 0);
        return hash;
    }
        
        
}
