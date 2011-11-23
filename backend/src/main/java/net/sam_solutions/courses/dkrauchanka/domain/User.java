package net.sam_solutions.courses.dkrauchanka.domain;

import java.util.HashSet;
import java.util.Set;
 
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.FetchType;

@Entity
@Table(name="users")
public class User {
	private Integer id;
	private String login;
	private String pass;
	private String firstName;
	private String lastName;
	private String role;
	private Set<Meeting> meetings = new HashSet<Meeting>(0);
	private Set<Task> tasks = new HashSet<Task>(0);
	
	public User(){
		
	}
	
	public User(Integer id, String login, String pass, String firstName, String lastName, String role){
		this.id = id;
		this.login = login;
		this.pass = pass;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}
	
	public void setMeeting(Meeting meeting) {
		this.meetings.add(meeting);
	}
	
	public void setTask(Task task) {
		this.tasks.add(task);
	}
	
	@Id
	@GeneratedValue
	@Column(name = "id_user")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "login", nullable = false, length = 45)
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	@Column(name = "pass", nullable = false, length = 45)
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	@Column(name = "FN", nullable = false, length = 45)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name = "LN", nullable = false, length = 45)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name = "role", nullable = false, length = 45)
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "users_has_meetings", joinColumns = { @JoinColumn(name = "users_id_user") }, inverseJoinColumns = { @JoinColumn(name = "meetings_id_meeting") })
	public Set<Meeting> getMeetings() {
		return meetings;
	}
	public void setMeetings(Set<Meeting> meetings) {
		this.meetings = meetings;
	}
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "tasks_has_users", joinColumns = { @JoinColumn(name = "users_id_user") }, inverseJoinColumns = { @JoinColumn(name = "tasks_id_task") })
	public Set<Task> getTasks() {
		return tasks;
	}
	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (pass == null) {
			if (other.pass != null)
				return false;
		} else if (!pass.equals(other.pass))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}
}
