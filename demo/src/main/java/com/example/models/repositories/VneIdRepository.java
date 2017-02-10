package com.example.models.repositories;

import com.example.models.nodes.VneId;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by ducna on 2/8/2017.
 */
public interface VneIdRepository extends GraphRepository<VneId> {
    VneId findByVneId(String vneId);
}
