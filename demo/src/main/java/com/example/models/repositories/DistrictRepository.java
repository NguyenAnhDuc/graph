package com.example.models.repositories;

import com.example.models.nodes.District;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by ducna on 2/8/2017.
 */
public interface DistrictRepository extends GraphRepository<District>{
    District findByDistrict(String district);
}
