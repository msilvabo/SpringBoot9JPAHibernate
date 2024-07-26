package com.learning.springboot9jpahibernate.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="persons")
@Builder
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    @SequenceGenerator(name = "person_seq", sequenceName = "persons_seq", allocationSize = 1)
    private Long id;

    private String name;
    private String lastname;

    @Column(name = "programming_language")
    private String programmingLanguage;
}