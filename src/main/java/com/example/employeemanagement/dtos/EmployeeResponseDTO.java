package com.example.employeemanagement.dtos;

import com.example.employeemanagement.models.Department;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmployeeResponseDTO {

    private Long id;
    private String name;
    private String city;
    private Date birthDate;
    private Department department;
}
