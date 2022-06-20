package com.example.employeemanagement.dtos;

import com.example.employeemanagement.models.Department;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessorResponseDTO {
    private Long id;
    private String name;
    private String city;
    private Department department;
}
