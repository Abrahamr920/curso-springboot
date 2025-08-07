package com.abraham.springboot.jpa.springboot_jpa_relationship.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import com.abraham.springboot.jpa.springboot_jpa_relationship.entities.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {
    @EntityGraph(attributePaths = { "students" })
    @Override
    Optional<Course> findById(Long id);
}
