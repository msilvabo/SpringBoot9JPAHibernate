package com.learning.springboot9jpahibernate;

import com.learning.springboot9jpahibernate.Repositories.PersonRepository;
import com.learning.springboot9jpahibernate.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class SpringBoot9JpaHibernateApplication implements CommandLineRunner {

    @Autowired
    private PersonRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot9JpaHibernateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        update();
    }

    @Transactional
    public void update(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el Id persona: ");
        Long id = scanner.nextLong();
        Optional<Person> optionalPerson = repository.findById(id);
        if (optionalPerson.isPresent()){
            Person person = optionalPerson.get();
            System.out.println(person);
            System.out.println("Ingrese lenguaje de programacion: ");
            String programmingLanguage = scanner.next();
            person.setProgrammingLanguage(programmingLanguage);
            Person personDB = repository.save(person);
            System.out.println("Actualiza11do " + personDB);
        } else{
            System.out.println("No existe el usuario...");
        }
        scanner.close();
    }

    @Transactional
    public void create(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre: ");
        String name = scanner.next();
        System.out.println("Ingrese el apellido: ");
        String lastname = scanner.next();
        System.out.println("Ingrese el lenguaje programación: ");
        String programmingLanguage = scanner.next();
        scanner.close();

        Person person = Person.builder()
                .name(name)
                .lastname(lastname)
                .programmingLanguage(programmingLanguage)
                .build();

        Person personNew = repository.save(person);
        System.out.println(personNew);

        repository.findById(personNew.getId()).ifPresent(person1 -> System.out.println("Persona encontrada: " + person1));
    }

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
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
