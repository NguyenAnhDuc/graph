package com.example.models;

import com.example.models.nodes.Email;
import com.example.models.nodes.FptId;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by ducna on 2/6/2017.
 */
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@RelationshipEntity(type = "HAS_EMAIL")
public class HasEmail {

    @GraphId
    private Long id;

    //private Collection<String> roles = new ArrayList<>();

    @StartNode
    private FptId fptId;

    @EndNode
    private Email email;

    public HasEmail() {
    }

    public HasEmail(FptId fptId, Email email) {
        this.fptId = fptId;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    /*public Collection<String> get() {
        return roles;
    }*/

    public FptId getFptId() {
        return fptId;
    }

    public Email getEmail() {
        return email;
    }

    /*public void addRoleName(String name) {
        this.roles.add(name);
    }*/
}
