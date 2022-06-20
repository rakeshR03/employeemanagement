package com.example.employeemanagement.controllers;

import com.example.employeemanagement.dtos.EmployeeDTO;
import com.example.employeemanagement.dtos.EmployeeResponseDTO;
import com.example.employeemanagement.models.Department;
import com.example.employeemanagement.models.Employee;
import com.example.employeemanagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/application/employees")
    public ResponseEntity<Object> registerEmployee(@RequestBody EmployeeDTO employeeDTO){
        String name = employeeDTO.getName();
        String city = employeeDTO.getCity();
        Date birthday = employeeDTO.getBirthday();
        Department department = employeeDTO.getDepartment();

        Employee employee = employeeService.registerEmployee(name, city, birthday, department);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(employee.getId()).toUri();


        return ResponseEntity.created(location).build();
    }

    @GetMapping("/application/employees/{id}")
    public EmployeeResponseDTO getEmployee(@PathVariable Long id){
        Employee employee = employeeService.getEmployee(id);

        EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
        employeeResponseDTO.setId(employee.getId());
        employeeResponseDTO.setName(employee.getName());
        employeeResponseDTO.setCity(employee.getCity());
        employeeResponseDTO.setBirthDate(employee.getBirthday());
        return employeeResponseDTO;

    }

    @DeleteMapping("/application/employees/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.OK).body("1 row deleted successfully");
    }

    @PutMapping("/application/employees/{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO){
        Employee employee = employeeService.updateEmployee(id, employeeDTO);
        if (employee != null) {
            return ResponseEntity.status(HttpStatus.OK).body("1 row updated");
        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("cannot update row");
        }

    }

    @GetMapping("/application/employees/getbydepartment/{id}")
    public List<EmployeeResponseDTO> findByDepartmentId(@PathVariable Long id){
        List<Employee> employees = employeeService.findByDepartmentId(id);

        List<EmployeeResponseDTO> employeeResponseDTOList = new ArrayList<>();

        for(Employee employee: employees){
            EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
            employeeResponseDTO.setId(employee.getId());
            employeeResponseDTO.setName(employee.getName());
            employeeResponseDTO.setCity(employee.getCity());
            employeeResponseDTO.setDepartment(employee.getDepartment());

            employeeResponseDTOList.add(employeeResponseDTO);
        }

        return employeeResponseDTOList;
    }

    @GetMapping("application/employees/{field}")
    public List<EmployeeResponseDTO> findAllByName(@PathVariable String field){
        List<Employee> employees = employeeService.findAllEmployeeSortedByName(field);

        List<EmployeeResponseDTO> employeeList = new ArrayList<>();

        for(Employee employee: employees){
            EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
            employeeResponseDTO.setId(employee.getId());
            employeeResponseDTO.setName(employee.getName());
            employeeResponseDTO.setCity(employee.getCity());
            employeeResponseDTO.setDepartment(employee.getDepartment());

            employeeList.add(employeeResponseDTO);
        }

        return employeeList;
    }

}
