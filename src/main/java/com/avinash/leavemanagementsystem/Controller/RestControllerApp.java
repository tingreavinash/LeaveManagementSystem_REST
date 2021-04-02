package com.avinash.leavemanagementsystem.Controller;

import com.avinash.leavemanagementsystem.Model.Employee;
import com.avinash.leavemanagementsystem.Model.LeaveRequest;
import com.avinash.leavemanagementsystem.Model.Manager;
import com.avinash.leavemanagementsystem.Service.EmployeeService;
import com.avinash.leavemanagementsystem.Service.LeaveRequestService;
import com.avinash.leavemanagementsystem.Service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "myproject/")
public class RestControllerApp {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    ManagerService managerService;
    @Autowired
    LeaveRequestService leaveRequestService;

    @RequestMapping(method = RequestMethod.GET, value = "/getEmployee/{id}")
    public Employee getEmployeeById(@PathVariable("id") String id) {
        try {
            return employeeService.getEmployeeById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAllManagers")
    public List<Manager> getAllManagers() {

        return managerService.getAllManager();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getManager/{id}")
    public Manager getManagerById(@PathVariable("id") String id) {

        return managerService.getManagerById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getallemp/{id}")
    public List<Employee> getEmployeeOfManager(@PathVariable("id") String id) {
        List<Employee> all_emp = employeeService.getEmployeesOfManager(id);
        return all_emp;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getusertype/{id}")
    public String getUserType(@PathVariable("id") String id) {
        try {
            return employeeService.getUserType(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getleaves/{id}")
    public List<LeaveRequest> getLeavesOfEmployee(@PathVariable("id") String id) {
        try {
            return leaveRequestService.getLeavesOfEmployee(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAllLeaves")
    public List<LeaveRequest> getAllLeaves() {
        return leaveRequestService.getAllLeaves();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAllleaves/{id}")
    public List<LeaveRequest> getChildLeaves(@PathVariable("id") String manager_id) {
        try {
            return leaveRequestService.getChildLeavesOfManager(manager_id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/setStatus/{userID}")
    public ResponseEntity<String> updateLeaveStatus(@RequestBody LeaveRequest leaveObj,
                                                    @PathVariable("userID") String userId) {
        System.out.println(getUserType(userId));
        if (leaveRequestService.updateLeaveStatus(leaveObj) != null)
            return new ResponseEntity<String>("Status Updated !", HttpStatus.OK);
        return new ResponseEntity<String>("Operation failed !", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addemp/{userID}")
    public ResponseEntity<String> addEmployee(@Valid @RequestBody Employee empObj, @PathVariable("userID") String userId) {
        System.out.println(getUserType(userId));
        try {
            if (employeeService.getEmployeeById(empObj.getEmpNtnet()) != null) {
                return new ResponseEntity<String>("Already Exists!", HttpStatus.BAD_REQUEST);
            } else {
                try {
                    employeeService.addEmployee(empObj);
                    return new ResponseEntity<String>("Employee Added !", HttpStatus.OK);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addmanager/{userID}")
    public ResponseEntity<String> addManager(@Valid @RequestBody Manager managerObj,
                                             @PathVariable("userID") String manager_id) {
        System.out.println(getUserType(manager_id));
        if (managerService.getManagerById(managerObj.getManagerNtnet()) != null) {
            return new ResponseEntity<String>("Already Exists!", HttpStatus.BAD_REQUEST);
        } else {
            if (managerService.addManager(managerObj) == true)
                return new ResponseEntity<String>("Manager Added !", HttpStatus.CREATED);
            else {
                return new ResponseEntity<String>("Operation failed !", HttpStatus.BAD_REQUEST);
            }
        }
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteemp/{emp_id}/{manager_id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("emp_id") String emp_id,
                                                 @PathVariable("manager_id") String manager_id) {
        System.out.println(getUserType(manager_id));
        if (employeeService.deleteEmployeeById(emp_id) == true) {
            return new ResponseEntity<String>("Employee Deleted along with corresponding leave data!", HttpStatus.OK);
        }

        return new ResponseEntity<String>("Operation failed !", HttpStatus.BAD_REQUEST);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/addleave/{userID}")
    public ResponseEntity<String> addLeaveRequest(@Valid @RequestBody LeaveRequest leaveObj,
                                                  @PathVariable("userID") String userId) {
        System.out.println(getUserType(userId));

        if (leaveRequestService.getLeavesByStartAndEndDate(
                leaveObj.getStartDate(),
                leaveObj.getEndDate(),
                leaveObj.getEmployee()).size() > 0) {
            return new ResponseEntity<String>("Leave already applied for given dates!", HttpStatus.BAD_REQUEST);
        } else {
            if (leaveRequestService.addLeaveRequest(leaveObj) == true)
                return new ResponseEntity<String>("Leave applied !", HttpStatus.CREATED);
            else {
                return new ResponseEntity<String>("Operation failed !", HttpStatus.BAD_REQUEST);
            }
        }
    }

}
