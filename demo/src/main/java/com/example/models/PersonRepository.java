package com.example.models;

import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by ducna on 2/6/2017.
 */
public interface PersonRepository extends GraphRepository<Person> {
    Person findByName(String name);
}
