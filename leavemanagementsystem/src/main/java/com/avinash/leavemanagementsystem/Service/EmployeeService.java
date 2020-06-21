package com.avinash.leavemanagementsystem.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.avinash.leavemanagementsystem.Model.Employee;
import com.avinash.leavemanagementsystem.Model.LeaveRequest;
import com.avinash.leavemanagementsystem.Model.Manager;
import com.avinash.leavemanagementsystem.Repository.EmployeeRepository;

@Component
public class EmployeeService{

	@Autowired
	EmployeeRepository empRepo;
	@Autowired ManagerService mgrService;
	@Autowired LeaveRequestService leaveService;

	public Employee addEmployee(Employee empObj) throws Exception{
		return empRepo.save(empObj);
	}
	
	
	
	public Boolean deleteEmployeeById(String emp_id) {
		try {
			List<LeaveRequest> lr_list = leaveService.getLeavesOfEmployee(emp_id);
			if (leaveService.deleteBatchLeaveRequest(lr_list)==true) {
				empRepo.deleteById(emp_id);	
				return true;
			}else {
				return false;
			}			
		}catch(Exception ex) {
			return false;
		}
		
	}
	
	public Employee getEmployeeById(String id) throws Exception {
		return empRepo.findById(id).get();
	}

	public List<Employee> getAllEmployee() {
		List<Employee> allEmp = new ArrayList<Employee>();
		empRepo.findAll().forEach(e -> allEmp.add(e));

		return allEmp;

	}
	
	public String getUserType(String user_id) throws Exception {
		String userType="Unauthorized";
		Employee e = this.getEmployeeById(user_id);
		Manager m = mgrService.getManagerById(user_id);
	
		if (e!=null 
				&& m!=null) {
			userType="M_E";
		}else if(e!=null 
				&& m==null) {
			userType="Employee";
		}else if (e==null 
				&& m!=null) {
			userType="Manager";
		}
	
		return userType;
	}
	public List<Employee> getEmployeesOfManager(String manager_id) {
		List<Employee> all_emp = new ArrayList<Employee>();

		Manager manager = mgrService.getManagerById(manager_id);
		List<Manager> mgr_list = mgrService.getAllManager();
		
		
		
		while(true) {
			List<Employee> lead_list = empRepo.findByManager(manager);
			all_emp.addAll(lead_list);
			for (Employee e1 : lead_list) {
				for (Manager m1 : mgr_list) {
					if((e1.getEmpNtnet()).equalsIgnoreCase(m1.getManagerNtnet())) {
						Manager sub_manager = mgrService.getManagerById(e1.getEmpNtnet());
						all_emp.addAll(empRepo.findByManager(sub_manager));
					}
				}
			}
			
			break;
		}
		

		return all_emp;
	}

}
