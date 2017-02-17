package com.example.models.repositories;

import com.example.models.nodes.Address;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by ducna on 2/6/2017.
 */
public interface AddressRepository extends GraphRepository<Address> {
    @Query("MATCH  (node:Address) WHERE node.address = {address} RETURN node")
    Address findByAddress(@Param("address") String address);
}
