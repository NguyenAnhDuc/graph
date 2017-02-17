package com.example.models.repositories;

import com.example.models.nodes.Province;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by ducna on 2/6/2017.
 */
public interface ProvinceRepository extends GraphRepository<Province> {
    @Query("MATCH  (node:Province) WHERE node.province = {province} RETURN node")
    Province findByProvince(@Param("province") String  province);
}
