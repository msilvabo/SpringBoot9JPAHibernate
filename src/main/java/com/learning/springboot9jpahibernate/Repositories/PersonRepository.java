package com.learning.springboot9jpahibernate.Repositories;

import com.learning.springboot9jpahibernate.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {
    List<Person> findByProgrammingLanguage(String programmigLanguaje);

    List<Person> findByProgrammingLanguageAndName(String programmigLanguaje,String name);

    @Query("select p from Person p where p.programmingLanguage = ?1 and p.name like %?2%")
    List<Person> buscarPorProgrammingLanguage(String programmigLanguaje, String name);

    @Query("select p.name, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonData();

    @Query("select p.name, p.programmingLanguage from Person p where p.programmingLanguage = ?1")
    List<Object[]> obtenerPersonData(String programmingLanguage);
}
