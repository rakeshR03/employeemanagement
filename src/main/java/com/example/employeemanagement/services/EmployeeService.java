package com.example.employeemanagement.services;

import com.example.employeemanagement.dtos.EmployeeDTO;
import com.example.employeemanagement.models.Department;
import com.example.employeemanagement.models.Employee;
import com.example.employeemanagement.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee registerEmployee(String name, String city, Date birthDay, Department department){
        //Employee object
        Employee employee = new Employee();
        employee.setName(name);
        employee.setCity(city);
        employee.setBirthday(birthDay);
        employee.setDepartment(department);

        Employee savedUser = null;

        try{
            savedUser = employeeRepository.save(employee);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return savedUser;
    }

    public Employee getEmployee(Long id){
        Optional<Employee> employee = employeeRepository.findById(id);
        if(!employee.isPresent()){
            throw new RuntimeException("employee is not found");
        }

        Employee retrievedEmployee = employee.get();

        return retrievedEmployee;
    }

    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }

    public Employee updateEmployee(Long id, EmployeeDTO employeeDTO){

        Optional<Employee> optional = employeeRepository.findById(id);
        if(!optional.isPresent()){
            throw new RuntimeException("employee is not found");
        }

        Employee employee = optional.get();

        if(employeeDTO.getName() != null){
            employee.setName(employeeDTO.getName());
        }

        if(employeeDTO.getCity() != null){
            employee.setCity(employeeDTO.getCity());
        }

        if(employeeDTO.getBirthday() != null){
            employee.setBirthday(employeeDTO.getBirthday());
        }

        if(employeeDTO.getDepartment() != null){
            employee.setDepartment(employeeDTO.getDepartment());
        }

        Employee updatedEmployee = employeeRepository.save(employee);

        return updatedEmployee;
    }

    public List<Employee> findByDepartmentId(Long id){
        return employeeRepository.findByDepartment_Id(id);
    }

    public List<Employee> findAllEmployeeSortedByCity(String city){
        return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, city));
    }

    public List<Employee> findAllEmployeeSortedByName(String name){
        return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, name));
    }

}
