package com.example.models.repositories;

import com.example.models.nodes.Province;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by ducna on 2/6/2017.
 */
public interface ProvinceRepository extends GraphRepository<Province> {
    Province findByProvince(String  province);
}
