package com.abraham.springboot.jpa.springboot_jpa_relationship.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import com.abraham.springboot.jpa.springboot_jpa_relationship.entities.ClientDetails;

public interface ClientDetailsRepository extends CrudRepository<ClientDetails, Long> {
    @EntityGraph(attributePaths = { "client" })
    @Override
    Optional<ClientDetails> findById(Long id);
}
