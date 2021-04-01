package com.avinash.leavemanagementsystem.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Employee")
public class Employee {
	@Id
	@Column(name="emp_ntnet")
	@NotBlank(message = "This is mandatory parameter.")
	private String empNtnet;
	@Column(name="emp_name")
	@NotBlank(message = "This is mandatory parameter.")
	private String empName;
	@Column(name="emp_email")
	@NotBlank(message = "This is mandatory parameter.")
	private String empEmail;
	@Column(name="emp_designation")
	@NotBlank(message = "This is mandatory parameter.")
	private String empDesignation;
	@Column(name="is_admin")
	private String isAdmin;
	@JsonIgnore
	@OneToMany (mappedBy = "employee", fetch = FetchType.EAGER)
	private List<LeaveRequest> leaverequest = new ArrayList<LeaveRequest>();

	@ManyToOne
	@NotNull(message = "Specify Manager details for given employee. 'managerNtnet' is mandatory")
	private Manager manager;
	

	public Employee(String empNtnet, String empName, String empEmail, String empDesignation, String isAdmin,
			List<LeaveRequest> leaverequest, Manager manager) {
		super();
		this.empNtnet = empNtnet;
		this.empName = empName;
		this.empEmail = empEmail;
		this.empDesignation = empDesignation;
		this.isAdmin = isAdmin;
		this.leaverequest = leaverequest;
		this.manager = manager;
	}


	@Override
	public String toString() {
		return "Employee [empNtnet=" + empNtnet + ", empName=" + empName + ", empEmail=" + empEmail
				+ ", empDesignation=" + empDesignation + ", isAdmin=" + isAdmin
				+ ", manager=" + manager + "]";
	}


	public String getEmpNtnet() {
		return empNtnet;
	}


	public void setEmpNtnet(String empNtnet) {
		this.empNtnet = empNtnet;
	}


	public String getEmpName() {
		return empName;
	}


	public void setEmpName(String empName) {
		this.empName = empName;
	}


	public String getEmpEmail() {
		return empEmail;
	}


	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}


	public String getEmpDesignation() {
		return empDesignation;
	}


	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}


	public String getIsAdmin() {
		return isAdmin;
	}


	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}


	public List<LeaveRequest> getLeaverequest() {
		return leaverequest;
	}


	public void setLeaverequest(List<LeaveRequest> leaverequest) {
		this.leaverequest = leaverequest;
	}


	public Manager getManager() {
		return manager;
	}


	public void setManager(Manager manager) {
		this.manager = manager;
	}


	Employee(){
		
	}
	
}
