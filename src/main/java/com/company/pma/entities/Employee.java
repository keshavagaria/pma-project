package com.company.pma.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.company.pma.validators.UniqueValue;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "employee_seq")
	@SequenceGenerator(name = "employee_seq", sequenceName = "employee_seq",
    allocationSize = 1,initialValue=1)
	private long employeeId;
	
	//@NotNull
	@NotBlank(message = "*First Name can not be blank")                            //client side validation
	@Size(min = 2,max = 50)		 //client side validation
	private String firstName;
	
	//@NotNull
	@NotBlank(message = "*Last Name can not be blank")
	@Size(min = 1,max = 50)
	private String lastName;
	
	//@NotNull
	@NotBlank
	@Email(message = "*Email must be valid")						//client side validation
	//@Column(unique = true)      //not client side validation
	@UniqueValue
	private String email;
	
	
	@ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},
			fetch = FetchType.LAZY)
	@JoinTable(name = "project_employee",
	joinColumns = @JoinColumn(name="employee_id"),
	inverseJoinColumns = @JoinColumn(name="project_id"))
	@JsonIgnore
	private List<Project> projects;
	
	
	public Employee() {
	}
	
	public Employee(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
