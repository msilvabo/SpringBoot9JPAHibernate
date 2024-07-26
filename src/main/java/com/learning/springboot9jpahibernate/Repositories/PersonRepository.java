package com.learning.springboot9jpahibernate.Repositories;

import com.learning.springboot9jpahibernate.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

    @Query("select CONCAT(p.name, ' ', p.lastname) from Person p where p.id = ?1")
    String getNameById(Long id);

    @Query("select p from Person p where p.id = ?1")
    Optional<Person> findOne(Long id);

    @Query("select p from Person p where p.name = ?1")
    Optional<Person> findOneName(String name);

    @Query("select p from Person p where p.name like %?1%")
    Optional<Person> findOneLikeName(String name);

    Optional<Person> findByNameContaining(String name);

    List<Person> findByProgrammingLanguage(String programmigLanguaje);

    List<Person> findByProgrammingLanguageAndName(String programmigLanguaje,String name);

    @Query("select p from Person p where p.programmingLanguage = ?1 and p.name like %?2%")
    List<Person> buscarPorProgrammingLanguage(String programmigLanguaje, String name);

    @Query("select p.name, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonData();

    @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonFullData();

    @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p where p.id = ?1")
    Object obtenerPersonFullDataById(Long id);

    @Query("select p.name, p.programmingLanguage from Person p where p.programmingLanguage = ?1")
    List<Object[]> obtenerPersonData(String programmingLanguage);

}
