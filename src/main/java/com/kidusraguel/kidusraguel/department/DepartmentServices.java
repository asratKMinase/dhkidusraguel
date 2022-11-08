package com.kidusraguel.kidusraguel.department;

import com.kidusraguel.kidusraguel.exceptions.InvalidRequestException;
import com.kidusraguel.kidusraguel.exceptions.ResourcePersistanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DepartmentServices {
    private DepartmentDao departmentDao;
    @Autowired
    public DepartmentServices(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }
    public List<Department> findAll(){
        List<Department> department= (List<Department>) departmentDao.findAll();
        return department;
    }
    public Department readById(Long id) {
        Department department= departmentDao.findById(id).get();
        return department;
    }
    public Department update(Department updatedDepartment) {
        departmentDao.save(updatedDepartment);
        return updatedDepartment;
    }
    public boolean delete(Long department) {
        departmentDao.deleteById(department);
        return true;
    }


    public Department create(Department newDepartment){
        if(!validateInput(newDepartment)){
            throw new InvalidRequestException("User input was not validated, either empty String or null values");
        }

        Department persistedDepartment = departmentDao.save(newDepartment);

        if(persistedDepartment == null){
            throw new ResourcePersistanceException("Challenges was not persisted to the database.");
        }
        return persistedDepartment;
    }

    public boolean validateInput(Department newDepartment) {
        if(newDepartment == null) return false;
        if(newDepartment.getDepid()== null || newDepartment.getDepid().equals("")) return false;
        if(newDepartment.getDepname()== null || newDepartment.getDepname().trim().equals("")) return false;
        if(newDepartment.getUid()== null || newDepartment.getUid().equals("")) return false;
        return newDepartment.getEmpid()!= null || !newDepartment.getEmpid().equals("");

    }
}

