package com.example.models.repositories;

import com.example.models.nodes.Phone;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by ducna on 2/6/2017.
 */
public interface PhoneRepository extends GraphRepository<Phone> {
    Phone findByPhone(String phone);
}
