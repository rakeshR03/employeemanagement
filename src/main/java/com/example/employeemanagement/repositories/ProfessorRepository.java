package com.example.employeemanagement.repositories;

import com.example.employeemanagement.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    @Override
    Professor save(Professor professor);

    @Override
    Optional<Professor> findById(Long aLong);

    @Override
    void deleteById(Long aLong);

    List<Professor> findByDepartment_Id(Long id);


}
