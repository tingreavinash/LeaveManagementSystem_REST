package com.avinash.leavemanagementsystem.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="Manager")
public class Manager {
	@Id
	@Column(name="manager_ntnet")
	@NotBlank(message = "This is mandatory parameter")
	private String managerNtnet;
	@Column(name="manager_name")
	@NotBlank(message = "This is mandatory parameter")
	private String managerName;
	@Column(name="manager_email")
	@NotBlank(message = "This is mandatory parameter")
	private String managerEmail;
	@Column(name="manager_designation")
	@NotBlank(message = "This is mandatory parameter")
	private String managerDesignation;
	@Column(name="is_admin")
	private String isAdmin;
	
	@Transient
	@OneToMany (mappedBy = "manager", fetch = FetchType.EAGER)
	private List<Employee> employee=new ArrayList<Employee>();

	@Override
	public String toString() {
		return "Manager [managerNtnet=" + managerNtnet + ", managerName=" + managerName + ", managerEmail="
				+ managerEmail + ", managerDesignation=" + managerDesignation + ", isAdmin=" + isAdmin + "]";
	}

	public String getManagerNtnet() {
		return managerNtnet;
	}

	public void setManagerNtnet(String managerNtnet) {
		this.managerNtnet = managerNtnet;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

	public String getManagerDesignation() {
		return managerDesignation;
	}

	public void setManagerDesignation(String managerDesignation) {
		this.managerDesignation = managerDesignation;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

	public Manager(String managerNtnet, String managerName, String managerEmail, String managerDesignation,
			String isAdmin, List<Employee> employee) {
		super();
		this.managerNtnet = managerNtnet;
		this.managerName = managerName;
		this.managerEmail = managerEmail;
		this.managerDesignation = managerDesignation;
		this.isAdmin = isAdmin;
		this.employee = employee;
	}

	Manager(){}
	

}
