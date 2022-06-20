package com.example.employeemanagement.services;

import com.example.employeemanagement.dtos.ProfessorDTO;
import com.example.employeemanagement.models.Department;
import com.example.employeemanagement.models.Professor;
import com.example.employeemanagement.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public Professor registerProfessor(String name, String city, Department department){

        Professor professor = new Professor();
        professor.setName(name);
        professor.setCity(city);
        professor.setDepartment(department);

        Professor savedProfessor = null;

        try{
            savedProfessor = professorRepository.save(professor);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return savedProfessor;
    }

    public Professor getProfessor(Long id){
        Optional<Professor> optional = professorRepository.findById(id);
        if(!optional.isPresent()){
            throw new RuntimeException("not found");
        }
        Professor professor = optional.get();

        return professor;
    }

    public void deleteProfessor(Long id){
        professorRepository.deleteById(id);
    }

    public Professor updateProfessor(Long id, ProfessorDTO professorDTO){
        Professor professor = getProfessor(id);
        if(professorDTO.getName() != null){
            professor.setName(professorDTO.getName());
        }
        if(professorDTO.getCity() != null){
            professor.setCity(professor.getCity());
        }
        if(professorDTO.getDepartment() != null){
            professor.setDepartment(professor.getDepartment());
        }

        Professor updatedProfessor = professorRepository.save(professor);

        return updatedProfessor;
    }

    public List<Professor> getAllProfessor(Long id){
        List<Professor> professors = professorRepository.findByDepartment_Id(id);
        return professors;
    }
}
