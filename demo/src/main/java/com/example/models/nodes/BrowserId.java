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
public class BrowserId {

    @GraphId
    private Long id;

    @Index(unique = true)
    private String browserId;

    private BrowserId() {
        // Empty constructor required as of Neo4j API 2.0.5
    };

    public BrowserId(String browserId) {
        this.browserId = browserId;
    }

    /**
     * Neo4j doesn't REALLY have bi-directional relationships. It just means when querying
     * to ignore the direction of the relationship.
     * https://dzone.com/articles/modelling-data-neo4j
     */
    @Relationship(type = "LINK_TO", direction = Relationship.INCOMING)
    public FptId fptId;
    public void linkToFptId(FptId fptId){
        this.fptId = fptId;
    }

    /*@Relationship(type = "LINK_TO", direction = Relationship.INCOMING)
    public Set<VioId> vioIds;
    public void linkToVioId(VioId vioId){
        if (vioIds == null) vioIds = new HashSet<>();
        vioIds.add(vioId);
    }*/

    @Relationship(type = "HAS_A", direction = Relationship.OUTGOING)
    public Email email;
    public void hasEmail(Email email) {
        this.email = email;
    }

    @Relationship(type = "HAS_A", direction = Relationship.OUTGOING)
    public Phone phone;

    public void hasPhone(Phone phone) {
        this.phone = phone;
    }

    @Relationship(type = "HAS_A", direction = Relationship.OUTGOING)
    public Province province;

    public void hasProvince(Province province) {
        this.province = province;
    }

    @Relationship(type = "HAS_A", direction = Relationship.OUTGOING)
    public Address address;

    public void hasAddress(Address address){
        this.address = address;
    }

    public String getBrowserId() {
        return browserId;
    }

    public void setBrowserId(String browserId) {
        this.browserId = browserId;
    }
}
