package com.example.models.nodes;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ducna on 2/8/2017.
 */
@NodeEntity
public class District {

    @GraphId
    private Long id;

    @Index(unique = true)
    private String district;

    private District() {
        // Empty constructor required as of Neo4j API 2.0.5
    };

    public District(String district) {
        this.district = district;
    }

    /*@Relationship(type = "HAS_A", direction = Relationship.INCOMING)
    public Set<FptId> fptIds;
    public void hasFptId(FptId fptId){
        if (fptIds == null) fptIds = new HashSet<>();
        fptIds.add(fptId);
    }*/

    @Relationship(type = "BELONGS_TO", direction = Relationship.INCOMING)
    public Province province;
    public void belongToProvince(Province province){
        this.province = province;
    }

    @Relationship(type = "BELONGS_TO", direction = Relationship.OUTGOING)
    public Set<Address> addresses;
    public void belongToAddress(Address address){
        if (this.addresses == null) this.addresses = new HashSet<>();
        this.addresses.add(address);
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
