package com.example.models.repositories;

import com.example.models.nodes.Address;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by ducna on 2/6/2017.
 */
public interface AddressRepository extends GraphRepository<Address> {
    Address findByAddress(String address);
}
