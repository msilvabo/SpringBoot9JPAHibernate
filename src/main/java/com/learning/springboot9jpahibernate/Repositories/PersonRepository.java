package com.learning.springboot9jpahibernate.Repositories;

import com.learning.springboot9jpahibernate.entities.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

}
