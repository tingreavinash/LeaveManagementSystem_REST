package com.avinash.leavemanagementsystem.Service;

import java.sql.Date;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.avinash.leavemanagementsystem.Model.Employee;
import com.avinash.leavemanagementsystem.Model.LeaveRequest;
import com.avinash.leavemanagementsystem.Repository.LeaveRequestRepository;

@Component
public class LeaveRequestService {
	@Autowired LeaveRequestRepository lrRepo;
	@Autowired EmployeeService empService;
	
	public LeaveRequest getLeaveById(int id) {
		if (lrRepo.findById(id) == null) return null;
		return lrRepo.findById(id).get();
	}
	
	public List<LeaveRequest> getLeavesByStartAndEndDate(Date start, Date end, Employee emp){
		List<LeaveRequest> lr_list = new ArrayList<LeaveRequest>();
		lrRepo.findByStartAndEndDate(start, end, emp).forEach(lr -> lr_list.add(lr));
		return lr_list;
	}
	
	public Boolean deleteBatchLeaveRequest(List<LeaveRequest> lr_list) {
		try{
			for (LeaveRequest leaveRequest : lr_list) {
				lrRepo.deleteById(leaveRequest.getLeaveReqId());
			}
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
	
	public List<LeaveRequest> getLeavesOfEmployee(String id) throws Exception{
		List<LeaveRequest> lr_list = new ArrayList<LeaveRequest>();
		Employee emp = empService.getEmployeeById(id);
		System.out.println(emp);
		lrRepo.findByEmployee(emp).forEach(lr -> lr_list.add(lr));
		return lr_list;
	}
	
	public List<LeaveRequest> getChildLeavesOfManager(String id) throws Exception{
		List<LeaveRequest> lr_list = new ArrayList<LeaveRequest>();
		List<Employee> emp_list = empService.getEmployeesOfManager(id);
		for (Employee employee : emp_list) {
			lr_list.addAll(this.getLeavesOfEmployee(employee.getEmpNtnet()));
		}
		System.out.println(lr_list.size());
		return lr_list;
	}
	
	public List<LeaveRequest> getAllLeaves() {
		List<LeaveRequest> lr_list = new ArrayList<LeaveRequest>();
		lrRepo.findAll().forEach(lr -> lr_list.add(lr));
		return lr_list;
		
	}
	
	public LeaveRequest updateLeaveStatus(LeaveRequest leaveRequest) {
		
		LeaveRequest lr = this.getLeaveById(leaveRequest.getLeaveReqId());
		if (lr !=null) {
			lr.setLeaveApprover(leaveRequest.getLeaveApprover());
			lr.setAckMsg(leaveRequest.getAckMsg());
			lr.setCurrentStatus(leaveRequest.getCurrentStatus());
			lrRepo.save(lr);
		}
		return lr;
	}
	
	public boolean addLeaveRequest(LeaveRequest lr) {
		try {
			long leave_duration= ( lr.getEndDate().getTime() - lr.getStartDate().getTime()) / (24 * 60 * 60 * 1000);
			lr.setDuration((int)leave_duration+1);
			lr.setCurrentStatus("Pending");
			lrRepo.save(lr);
			return true;
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}
}
