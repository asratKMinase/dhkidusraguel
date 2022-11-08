package com.kidusraguel.kidusraguel.department;


import com.kidusraguel.kidusraguel.employee.EmployeeServices;
import com.kidusraguel.kidusraguel.users.Users;
import com.kidusraguel.kidusraguel.users.UsersServices;
import com.kidusraguel.kidusraguel.util.web.dto.DepartmentInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/department")
public class DepartmentServlet {
    private final DepartmentServices departmentServices;
    private final UsersServices usersServices;
    private final EmployeeServices employeeServices;

    @Autowired
    public DepartmentServlet(DepartmentServices departmentServices, UsersServices usersServices, EmployeeServices employeeServices) {
        this.departmentServices = departmentServices;
        this.usersServices = usersServices;
        this.employeeServices = employeeServices;
    }

    @GetMapping("/findAllDepartment")
    public ResponseEntity<List> findAllDepartment() {
        return new ResponseEntity<>(departmentServices.findAll(), HttpStatus.FOUND);
    }

    @GetMapping("/findDepartment")
    public ResponseEntity<Department> findDepartment(@RequestParam Long depid) {
        Department department = departmentServices.readById(depid);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Department> CreateDepartment(@RequestBody DepartmentInitializer newDepartmenti, HttpSession req) {

        Department newDepartment = new Department();
        Users authAddress = (Users) req.getAttribute("authUClasses");
        newDepartment.setDepid(newDepartmenti.getDepid());
        newDepartment.setDepname(newDepartmenti.getDepname());
        newDepartment.setUid(usersServices.readById(newDepartmenti.getUid()));
        newDepartment.setEmpid(employeeServices.readById(newDepartmenti.getEmpid()));

        Department department= departmentServices.create(newDepartment);
        return new ResponseEntity<>(department, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Department> updateDepartment(@RequestBody DepartmentInitializer newDepartmenti, HttpSession req) {
        Department newDepartment = new Department();
        Users authAddress = (Users) req.getAttribute("authUClasses");
        newDepartment.setDepid(newDepartmenti.getDepid());
        newDepartment.setDepname(newDepartmenti.getDepname());
        newDepartment.setUid(usersServices.readById(newDepartmenti.getUid()));
        newDepartment.setEmpid(employeeServices.readById(newDepartmenti.getEmpid()));

        Department department= departmentServices.create(newDepartment);
        return new ResponseEntity<>(department, HttpStatus.CREATED);

    }
    @DeleteMapping("/delete")
    public void deleteDepartment(@RequestParam Long depid) {
        boolean newChallenge= departmentServices.delete(depid);
    }

}
