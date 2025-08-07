package com.abraham.springboot.jpa.springboot_jpa_relationship.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import com.abraham.springboot.jpa.springboot_jpa_relationship.entities.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
    @EntityGraph(attributePaths = { "courses" })
    @Override
    Optional<Student> findById(Long id);

}
