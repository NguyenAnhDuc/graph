package com.example.models.repositories;

import com.example.models.nodes.Email;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by ducna on 2/6/2017.
 */
public interface EmailRepository extends GraphRepository<Email> {
    Email findByEmail(String email);
}