package com.kidusraguel.kidusraguel.employee;

import com.kidusraguel.kidusraguel.exceptions.InvalidRequestException;
import com.kidusraguel.kidusraguel.exceptions.ResourcePersistanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeeServices  {
    private EmployeeDao employeeDao;

    @Autowired
    public EmployeeServices(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public List<Employee> findAll(){
        List<Employee> employee = (List<Employee>) employeeDao.findAll();
        return employee;
    }
    public Employee readById(long id) {
        Employee employee = employeeDao.findById(id).get();
        return employee;
    }
    public Employee update(Employee updatedEmployee) {
        employeeDao.save(updatedEmployee);
        return updatedEmployee;
    }
    public boolean delete(long id) {
        employeeDao.deleteById(id);
        return true;
    }

    public Employee create(Employee newEmployee){
        if(!validateInput(newEmployee)){
            throw new InvalidRequestException("Employee input was not validated, either empty String or null values");
        }

        Employee persistedEmployee= employeeDao.save(newEmployee);

        if(persistedEmployee == null){
            throw new ResourcePersistanceException("Customer was not persisted to the database upon registration");
        }
        return persistedEmployee;
    }
    public boolean validateInput(Employee newEmployee) {

        if(newEmployee == null) return false;
        if(newEmployee.getId()== null || newEmployee.getId().equals("")) return false;
        if(newEmployee.getTitle() == null || newEmployee.getTitle().trim().equals("")) return false;
        if(newEmployee.getHdate() == null || newEmployee.getHdate().trim().equals("")) return false;
        return newEmployee.getId()!= null || !newEmployee.getId().equals("");
    }

}
