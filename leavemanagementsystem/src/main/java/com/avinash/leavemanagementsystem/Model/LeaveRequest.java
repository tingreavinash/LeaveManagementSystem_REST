package com.avinash.leavemanagementsystem.Model;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="LeaveRequest")
public class LeaveRequest {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name="leave_req_id")
	private int leaveReqId;
	@Column(name="duration")
	private int duration;
	@Column(name="start_date")
	@NotNull(message = "This is mandatory field.")
	private Date startDate;
	@Column(name="end_date")
	@NotNull(message = "This is mandatory field.")
	private Date endDate;
	@Column(name="leave_type")
	@NotNull(message = "This is mandatory field.")
	private String leaveType;
	@Column(name="leave_description")
	private String leaveDescription;
	@Column(name="current_status", 
			columnDefinition = "varchar(255) default 'Pending'")
	private String currentStatus;
	@Column(name="leave_approver")
	private String leaveApprover;
	@Column(name="ack_msg")
	private String ackMsg;
	@ManyToOne
	@NotNull(message = "This is mandatory field.")
	private Employee employee;
	//@OneToOne (cascade=CascadeType.ALL)
	//private LeaveStatus leavestatus;
	LeaveRequest(){	}
	public int getLeaveReqId() {
		return leaveReqId;
	}
	public void setLeaveReqId(int leaveReqId) {
		this.leaveReqId = leaveReqId;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public String getLeaveDescription() {
		return leaveDescription;
	}
	public void setLeaveDescription(String leaveDescription) {
		this.leaveDescription = leaveDescription;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	public String getLeaveApprover() {
		return leaveApprover;
	}
	public void setLeaveApprover(String leaveApprover) {
		this.leaveApprover = leaveApprover;
	}
	public String getAckMsg() {
		return ackMsg;
	}
	public void setAckMsg(String ackMsg) {
		this.ackMsg = ackMsg;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	@Override
	public String toString() {
		return "LeaveRequest [leaveReqId=" + leaveReqId + ", duration=" + duration + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", leaveType=" + leaveType + ", leaveDescription=" + leaveDescription
				+ ", currentStatus=" + currentStatus + ", leaveApprover=" + leaveApprover + ", ackMsg=" + ackMsg
				+ ", employee=" + employee + "]";
	}
	public LeaveRequest(int leaveReqId, int duration, Date startDate, Date endDate, String leaveType,
			String leaveDescription, String currentStatus, String leaveApprover, String ackMsg, Employee employee) {
		super();
		this.leaveReqId = leaveReqId;
		this.duration = duration;
		this.startDate = startDate;
		this.endDate = endDate;
		this.leaveType = leaveType;
		this.leaveDescription = leaveDescription;
		this.currentStatus = currentStatus;
		this.leaveApprover = leaveApprover;
		this.ackMsg = ackMsg;
		this.employee = employee;
	}
	
		
}
