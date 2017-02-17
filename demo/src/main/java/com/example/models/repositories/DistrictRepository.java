package com.example.models.repositories;

import com.example.models.nodes.District;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by ducna on 2/8/2017.
 */
public interface DistrictRepository extends GraphRepository<District>{
    @Query("MATCH  (node:District) WHERE node.district = {district} RETURN node")
    District findByDistrict(@Param("district") String district);
}
