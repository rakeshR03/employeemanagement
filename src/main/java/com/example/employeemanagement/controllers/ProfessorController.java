package com.example.employeemanagement.controllers;

import com.example.employeemanagement.dtos.ProfessorDTO;
import com.example.employeemanagement.dtos.ProfessorResponseDTO;
import com.example.employeemanagement.models.Department;
import com.example.employeemanagement.models.Professor;
import com.example.employeemanagement.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping("/application/professors")
    public ResponseEntity<Object> registerProfessor(@RequestBody ProfessorDTO professorDTO){

        String name = professorDTO.getName();
        String city = professorDTO.getCity();
        Department department = professorDTO.getDepartment();

        Professor professor = professorService.registerProfessor(name, city, department);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").
                buildAndExpand(professor.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/application/professors/{id}")
    public ProfessorResponseDTO getProfessor(@PathVariable Long id){

        Professor professor = professorService.getProfessor(id);
        ProfessorResponseDTO professorResponseDTO = new ProfessorResponseDTO();
        professorResponseDTO.setId(professor.getId());
        professorResponseDTO.setName(professor.getName());
        professorResponseDTO.setCity(professor.getCity());
        professorResponseDTO.setDepartment(professor.getDepartment());

        return professorResponseDTO;
    }

    @DeleteMapping("/application/professors/{id}")
    public ResponseEntity<Object> deleteProfessor(@PathVariable Long id){
        professorService.deleteProfessor(id);
        return ResponseEntity.status(HttpStatus.OK).body("1 row deleted");
    }

    @PutMapping("/application/professors/{id}")
    public ResponseEntity<Object> updateProfessor(@PathVariable Long id, @RequestBody ProfessorDTO professorDTO){
        Professor professor = professorService.updateProfessor(id, professorDTO);

        if (professor != null) {
            return ResponseEntity.status(HttpStatus.OK).body("1 row updated");
        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("cannot update row");
        }
    }

    @GetMapping("/application/professors/getByDepartment/{id}")
    public List<ProfessorResponseDTO> getProfessorByDepartment(@PathVariable Long id){
        List<Professor> professors = professorService.getAllProfessor(id);

        List<ProfessorResponseDTO> professorResponseDTOList = new ArrayList<>();

        for(Professor professor : professors){
            ProfessorResponseDTO professorDTO = new ProfessorResponseDTO();
            professorDTO.setName(professor.getName());
            professorDTO.setId(professor.getId());
            professorDTO.setCity(professor.getCity());
            professorDTO.setDepartment(professor.getDepartment());

            professorResponseDTOList.add(professorDTO);
        }

        return professorResponseDTOList;
    }


}
