package com.avinash.leavemanagementsystem.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.avinash.leavemanagementsystem.Model.Manager;

@Repository
public interface ManagerRepository extends CrudRepository<Manager, String>{
	
}
