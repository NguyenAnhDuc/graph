package com.example.models.repositories;

import com.example.models.nodes.VioId;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by ducna on 2/7/2017.
 */
public interface VioIdRepository extends GraphRepository<VioId> {
    VioId findByVioId(int vioId);
}
