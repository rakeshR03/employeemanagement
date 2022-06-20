package com.example.employeemanagement.controllers;

import com.example.employeemanagement.dtos.DepartmentResponseDTO;
import com.example.employeemanagement.models.Department;
import com.example.employeemanagement.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/application/departments")
    public ResponseEntity<Object> registerDepartment(@RequestBody String name){
        Department department = departmentService.registerDepartment(name);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(department.getId()).toUri();


        return ResponseEntity.created(location).build();
    }

    @GetMapping("/application/departments/{id}")
    public DepartmentResponseDTO getDepartment(@PathVariable Long id){
        Department department = departmentService.getDepartment(id);

        DepartmentResponseDTO departmentResponseDTO = new DepartmentResponseDTO();
        departmentResponseDTO.setId(department.getId());
        departmentResponseDTO.setName(department.getName());
        return departmentResponseDTO;
    }

    @DeleteMapping("/application/departments/{id}")
    public ResponseEntity<Object> deleteDepartment(@PathVariable Long id){
        departmentService.deleteDepartment(id);
        return ResponseEntity.status(HttpStatus.OK).body("1 row deleted successfully");
    }

    @PutMapping("/application/departments/{id}")
    public ResponseEntity<Object> updateDepartment(@PathVariable Long id, @RequestBody String name){
        Department department = departmentService.updateDepartment(id, name);
        if (department != null) {
            return ResponseEntity.status(HttpStatus.OK).body("1 row updated");
        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("cannot update row");
        }

    }
}
