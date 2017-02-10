package com.example.models.repositories;

import com.example.models.nodes.FptId;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by ducna on 2/6/2017.
 */
public interface FptIdRepository extends GraphRepository<FptId> {
    //@Query()
    FptId findByFptId(String fptId);
}
