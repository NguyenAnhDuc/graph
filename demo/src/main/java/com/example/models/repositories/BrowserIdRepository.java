package com.example.models.repositories;

import com.example.models.nodes.BrowserId;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by ducna on 2/8/2017.
 */
public interface BrowserIdRepository extends GraphRepository<BrowserId>{
    BrowserId findByBrowserId(String browserId);
}
