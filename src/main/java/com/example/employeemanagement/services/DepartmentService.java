package com.example.employeemanagement.services;

import com.example.employeemanagement.models.Department;
import com.example.employeemanagement.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department registerDepartment(String name){

        Department department = new Department();
        department.setName(name);

        Department savedDepartment = null;

        try{
            savedDepartment = departmentRepository.save(department);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return savedDepartment;
    }

    public Department getDepartment(Long id){
        Optional<Department> optional  = departmentRepository.findById(id);

        if(!optional.isPresent()){
            throw new RuntimeException("employee is not found");
        }

        Department department = optional.get();

        return department;
    }

    public void deleteDepartment(Long id){
        departmentRepository.deleteById(id);
    }

    public Department updateDepartment(Long id, String name){
        Optional<Department> optional = departmentRepository.findById(id);
        if(!optional.isPresent()){
            throw new RuntimeException("employee is not found");
        }

        Department department = optional.get();

        if(name != null){
            department.setName(name);
        }

        Department savedDepartment = departmentRepository.save(department);

        return savedDepartment;
    }
}
