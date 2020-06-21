package com.avinash.leavemanagementsystem.Repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.avinash.leavemanagementsystem.Model.Employee;
import com.avinash.leavemanagementsystem.Model.LeaveRequest;

@Repository
public interface LeaveRequestRepository extends CrudRepository<LeaveRequest, Integer> {
	
	List<LeaveRequest> findByEmployee(Employee employee);
	
	@Query("select u from LeaveRequest u where u.startDate=?1 and u.endDate=?2 and u.employee=?3")
	List<LeaveRequest> findByStartAndEndDate (Date startDate, Date endDate, Employee employee);
	
}
