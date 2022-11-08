package com.kidusraguel.kidusraguel.employee;

import com.kidusraguel.kidusraguel.users.Users;
import com.kidusraguel.kidusraguel.users.UsersServices;
import com.kidusraguel.kidusraguel.util.web.dto.EmployeeInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/employee")
public class EmployeeServlet {
    private final EmployeeServices employeeServices;
    private final UsersServices usersServices;

    @Autowired
    public EmployeeServlet(EmployeeServices employeeServices, UsersServices usersServices) {
        this.employeeServices = employeeServices;
        this.usersServices = usersServices;
    }

    @GetMapping("/findAllEmployee")
    public ResponseEntity<List> findAllEmployee() {
        return new ResponseEntity<>(employeeServices.findAll(), HttpStatus.FOUND);
    }

    @GetMapping("/findEmployee")
    public ResponseEntity<Employee> findEmployee(@RequestParam Long empid) {
        Employee employee = employeeServices.readById(empid);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Employee> CreateEmployee(@RequestBody EmployeeInitializer newEMployeei, HttpSession req) {

        Employee newEmployee = new Employee();
        Users authClasses = (Users) req.getAttribute("authUClasses");
        newEmployee.setId(newEMployeei.getId());
        newEmployee.setTitle(newEMployeei.getTitle());
        newEmployee.setHdate(newEMployeei.getHdate());
        newEmployee.setUid(usersServices.readById(newEMployeei.getUid()));

        Employee employee = employeeServices.create(newEmployee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateCourses(@RequestBody EmployeeInitializer newEMployeei, HttpSession req) {
        Employee newEmployee = new Employee();
        Users authClasses = (Users) req.getAttribute("authUClasses");
        newEmployee.setId(newEMployeei.getId());
        newEmployee.setTitle(newEMployeei.getTitle());
        newEmployee.setHdate(newEMployeei.getHdate());
        newEmployee.setUid(usersServices.readById(newEMployeei.getUid()));

        Employee employee = employeeServices.create(newEmployee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete")
    public void deleteCourse(@RequestParam Long empid) {
        boolean newCourse = employeeServices.delete(empid);
    }

}

