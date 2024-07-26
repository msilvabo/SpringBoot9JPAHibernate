package com.learning.springboot9jpahibernate;

import com.learning.springboot9jpahibernate.Repositories.PersonRepository;
import com.learning.springboot9jpahibernate.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpringBoot9JpaHibernateApplication implements CommandLineRunner {

    @Autowired
    private PersonRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot9JpaHibernateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        create();
        list();
    }

    public void create(){
        Person person = Person.builder()
                .name("Moises")
                .lastname("Silva")
                .programmingLanguage("Java")
                .build();

        Person personNew = repository.save(person);
    }

    public void findOne(long id){
//        Person person = null;
//        Optional<Person> optionalPerson = repository.findById(id);
//        if(optionalPerson.isPresent()){
//            person = optionalPerson.get();
//            System.out.println(person);
//        } else {
//            System.out.println("Person not found");
//        }
        repository.findByNameContaining("an").ifPresent(person -> System.out.println(person));
//        repository.findById(id).ifPresent(System.out::println);
    }


    public void list(){
        List<Person> persons = (List<Person>) repository.findByProgrammingLanguageAndName("Java", "Moises");
        System.out.println("---- LISTADO DE PERSONAS ----");
        persons.stream().forEach(person -> System.out.println(person));

        System.out.println("---- LISTADO DE DATOS PERSONA ----");
        List<Object[]> listado = repository.obtenerPersonData("Java");
        listado.stream().forEach(objects -> {
            System.out.println(objects[0] + " es experto en " + objects[1] );
        });
    }
}
