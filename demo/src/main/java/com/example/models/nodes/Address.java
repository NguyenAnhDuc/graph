package com.example.models.nodes;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ducna on 2/6/2017.
 */
@NodeEntity
public class Address {

    @GraphId
    private Long id;

    @Index(unique = true)
    private String address;

    private Address() {
        // Empty constructor required as of Neo4j API 2.0.5
    };

    public Address(String address) {
        this.address = address;
    }

    /*@Relationship(type = "HAS_A", direction = Relationship.INCOMING)
    public Set<FptId> fptIds;
    public void hasFptId(FptId fptId){
        if (this.fptIds == null) fptIds = new HashSet<>();
        this.fptIds.add(fptId);
    }*/

    @Relationship(type = "BELONGS_TO", direction = Relationship.INCOMING)
    public District district;
    public void belongToDistrict(District district){
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
