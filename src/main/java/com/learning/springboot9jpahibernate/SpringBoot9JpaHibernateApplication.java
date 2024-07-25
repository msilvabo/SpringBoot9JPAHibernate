package com.learning.springboot9jpahibernate;

import com.learning.springboot9jpahibernate.Repositories.PersonRepository;
import com.learning.springboot9jpahibernate.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringBoot9JpaHibernateApplication implements CommandLineRunner {

    @Autowired
    private PersonRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot9JpaHibernateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        List<Person> persons = (List<Person>) repository.findAll();
        List<Person> persons = (List<Person>) repository.findByProgrammingLanguageAndName("Java", "Eve");
        System.out.println("---- LISTADO DE PERSONAS ----");
        persons.stream().forEach(person -> System.out.println(person));

        System.out.println("---- LISTADO DE DATOS PERSONA ----");
        List<Object[]> listado = repository.obtenerPersonData("Java");
        listado.stream().forEach(objects -> {
            System.out.println(objects[0] + " es experto en " + objects[1] );
        });
    }
}
