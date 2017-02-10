package com.example.models.nodes;

/**
 * Created by ducna on 2/6/2017.
 */

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Email {

    @GraphId private Long id;

    private String email;

    private Email() {
        // Empty constructor required as of Neo4j API 2.0.5
    };

    @Relationship(type = "HAS_A", direction = Relationship.INCOMING)
    public FptId fptId;
    public void hasFptId(FptId fptId){
        this.fptId = fptId;
    }



    public Email(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}