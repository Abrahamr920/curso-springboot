package com.springboot.jpa.springboot_jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.springboot.jpa.springboot_jpa.dto.PersonDto;
import com.springboot.jpa.springboot_jpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findByLastName(String lastName);

    List<Person> findByName(String firstName);

    List<Person> findByProgrammingLanguage(String programmingLanguage);

    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

    List<Person> findByNameContaining(String name);

    List<Person> findByNameStartingWith(String prefix);

    Optional<Person> findFirstByNameContaining(String name);

    @Query("select p.name, p.programmingLanguage from Person p") // solo quiere dos datos de la tabla
    List<Object[]> obtenerPersonData();

    @Query("select p.name, p.programmingLanguage from Person p " +
            "where p.programmingLanguage = ?1 and p.name = ?2") // solo quiere dos datos de la tabla
    List<Object[]> getPersonData(String programmingLanguage, String name);

    @Query("select p.id, p.name, p.lastName, p.programmingLanguage from Person p where p.id = ?1")
    Object getFullPersonData(Long id);// Para object individual no pongo [], ese solo en list

    @Query("select p from Person p where p.id = ?1") // optional por si devuelve null
    Optional<Person> findOne(Long id);

    @Query("select p from Person p where p.name like %?1%")
    List<Person> findLikeName(String name);

    // @Query("select CONCAT(p.name, ' ', p.lastName) from Person p where p.id =
    // ?1")
    @Query("select p.name || ' ' || p.lastName from Person p where p.id = ?1")
    String getFullNamePersonById(Long id);

    @Query("select p, p.programmingLanguage from Person p")
    List<Object[]> findTwoProgrammingLanguaje();

    @Query("select new Person(p.name, p.lastName) from Person p")
    List<Person> findAllFullName();

    @Query("select new com.springboot.jpa.springboot_jpa.dto.PersonDto(p.name, p.lastName) from Person p")
    List<PersonDto> findAllPersonDto();

    @Query("select p from Person p")
    List<Person> buscarTodos();

    @Query("SELECT DISTINCT p.name FROM Person p")
    List<String> findAllDistinctNames();

    @Query("select count(distinct p.programmingLanguage) from Person p")
    Long findCountOfDistinctProgrammingLanguage();

    @Query("select distinct p.programmingLanguage from Person p")
    List<String> findDistinctProgrammingLanguage();

    @Query("select upper(p.name) from Person p")
    List<String> findAllUpperNames();

    @Query("select lower(p.name) from Person p")
    List<String> findAllLowerNames();

    @Query("SELECT p FROM Person p WHERE p.name BETWEEN ?1 AND ?2")
    List<Person> findPersonsByNameRange(String startName, String endName);

    List<Person> findByNameBetween(String startName, String endName);

    @Query("SELECT p FROM Person p ORDER BY p.name DESC")
    List<Person> findAllOrderedByName();

    @Query("SELECT p FROM Person p ORDER BY p.name ASC, p.lastName ASC")
    List<Person> findAllOrderedByLastNameAndName();

    List<Person> findAllByOrderByNameAscLastNameAsc();

    @Query("SELECT COUNT(p) FROM Person p")
    long countAllPersons();

    // long count();

    @Query("SELECT MAX(p.id) FROM Person p")
    Long findMaxPersonId();

    Person findTopByOrderByIdDesc();

    @Query("SELECT MIN(p.id) FROM Person p")
    Long findMinPersonId();

    @Query("SELECT AVG(p.id) FROM Person p")
    Double findAverageId();

    @Query("SELECT SUM(p.id) FROM Person p")
    Double findTotalId();

    @Query("SELECT p FROM Person p WHERE LENGTH(p.name) =3")
    List<Person> findPersonsWithLongName();

    @Query("SELECT MAX(p.id), MIN(p.id), AVG(p.id), SUM(p.id) FROM Person p")
    Object findAggregateFunctions();

    @Query("SELECT p.name, LENGTH(p.name) FROM Person p WHERE LENGTH(p.name) = (SELECT MAX(LENGTH(p2.name)) FROM Person p2) ORDER BY LENGTH(p.name) DESC")
    List<String> findLongestName();

    @Query("SELECT p.name, LENGTH(p.name) FROM Person p WHERE LENGTH(p.name) = (SELECT MIN(LENGTH(p2.name)) FROM Person p2)")
    List<String> findShortestName();

    @Query("SELECT p.name, LENGTH(p.name) FROM Person p WHERE LENGTH(p.name) = (SELECT MAX(LENGTH(p.name)) FROM Person p) ORDER BY LENGTH(p.name) DESC")
    List<Object[]> findLongestName1();

    @Query("select p from Person p where p.id=(select max(p.id) from Person p)")
    Optional<Person> findLastPersonRegistration();

    public List<Person> findByNameContainingIgnoreCase(String name);

    @Query("SELECT p FROM Person p WHERE p.programmingLanguage IN :pL")
    List<Person> findPersonsByProgrammingLanguaje(@Param("pL") List<String> programmingLanguajes);
}
