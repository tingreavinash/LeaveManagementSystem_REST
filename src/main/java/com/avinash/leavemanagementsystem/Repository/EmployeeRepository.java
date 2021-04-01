package com.avinash.leavemanagementsystem.Repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.avinash.leavemanagementsystem.Model.Employee;
import com.avinash.leavemanagementsystem.Model.Manager;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String>{
	
	
	List<Employee> findByManager(Manager manager);
}
