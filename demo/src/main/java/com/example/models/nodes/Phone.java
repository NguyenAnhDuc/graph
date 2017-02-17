package com.example.models.nodes;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by ducna on 2/6/2017.
 */
@NodeEntity
public class Phone {

    @GraphId
    private Long id;

    @Index(unique = true)
    private String phone;

    private Phone() {
        // Empty constructor required as of Neo4j API 2.0.5
    };

    public Phone(String phone) {
        this.phone = phone;
    }

    @Relationship(type = "HAS_A", direction = Relationship.INCOMING)
    public FptId fptId;
    public void hasFptId(FptId fptId){
        this.fptId = fptId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}