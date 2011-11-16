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
import javax.persistence.Table;

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
}
