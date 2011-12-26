package net.sam_solutions.courses.dkrauchanka.domain;

import java.util.HashSet;
import java.util.Set;
 
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import net.sam_solutions.courses.dkrauchanka.utils.Password;

@Entity
@Table(name="users")
public class User {
	private String login;
	private String pass;
	private String firstName;
	private String lastName;
	private Role role;
	
	public User(){
		
	}
	
	public User(String login, String pass, String firstName, String lastName, Role role){
		this.login = login;
		this.pass = pass;
		this.firstName = firstName;
		this.lastName = lastName;
                this.role = role;
	}	
	
	@Id
	@Column(name = "users_login", length = 45)
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	@Column(name = "users_pass", length = 40)
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	@Column(name = "users_FN", length = 45)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name = "users_LN", length = 45)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
        @ManyToOne
        @JoinColumn(name = "users_role", referencedColumnName = "roles_role")
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if ((this.login == null) ? (other.login != null) : !this.login.equals(other.login)) {
            return false;
        }
        if ((this.pass == null) ? (other.pass != null) : !this.pass.equals(other.pass)) {
            return false;
        }
        if ((this.firstName == null) ? (other.firstName != null) : !this.firstName.equals(other.firstName)) {
            return false;
        }
        if ((this.lastName == null) ? (other.lastName != null) : !this.lastName.equals(other.lastName)) {
            return false;
        }
        if (!this.role.equals(other.getRole())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.login != null ? this.login.hashCode() : 0);
        hash = 59 * hash + (this.pass != null ? this.pass.hashCode() : 0);
        hash = 59 * hash + (this.firstName != null ? this.firstName.hashCode() : 0);
        hash = 59 * hash + (this.lastName != null ? this.lastName.hashCode() : 0);
        hash = 59 * hash + (this.role != null ? this.role.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "User{" + "login=" + login + ", pass=" + pass + ", firstName=" + firstName + ", lastName=" + lastName + ", role=" + role + '}';
    }

    
}
