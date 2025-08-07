package com.abraham.springboot.jpa.springboot_jpa_relationship.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.abraham.springboot.jpa.springboot_jpa_relationship.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @EntityGraph(attributePaths = { "addresses", "invoices", "clientDetails" })
    @Override
    Optional<Client> findById(Long id);
}
