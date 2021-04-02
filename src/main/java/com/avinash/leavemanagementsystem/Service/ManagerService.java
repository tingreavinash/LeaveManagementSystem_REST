package com.avinash.leavemanagementsystem.Service;

import com.avinash.leavemanagementsystem.Model.Manager;
import com.avinash.leavemanagementsystem.Repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class ManagerService {
    @Autowired
    ManagerRepository managerRepo;

    public Boolean addManager(Manager managerObj) {
        try {
            managerRepo.save(managerObj);
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public Manager getManagerById(String id) throws NoSuchElementException {
        if (managerRepo.findById(id) == null) {
            return null;
        }
        return managerRepo.findById(id).get();
    }

    public List<Manager> getAllManager() {
        List<Manager> manager_list = new ArrayList<Manager>();
        managerRepo.findAll().forEach(m -> manager_list.add(m));
        return manager_list;
    }
}
