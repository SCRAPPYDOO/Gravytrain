package gravytrain.repository.user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import gravytrain.exceptions.ActiveUserException;

@Entity
@Table(name = "USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;

	private String firstname;

	private String surname;

	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	private boolean isActive;

	public User() {
	}

	public User(String title, String firstname, String surname, Date dateOfBirth, boolean isActive) {
		super();
		this.title = title;
		this.firstname = firstname;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.isActive = isActive;
	}

	public User(int id, String title, String firstname, String surname, Date dateOfBirth, boolean isActive) {
		super();
		this.id = id;
		this.title = title;
		this.firstname = firstname;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.isActive = isActive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	@PreRemove
	private void preRemove() throws ActiveUserException {
	    if (isActive) {
	        throw new ActiveUserException();
	    }
	}
}
