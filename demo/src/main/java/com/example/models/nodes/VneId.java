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
public class VneId {

    @GraphId
    private Long id;

    @Index(unique = true)
    private String vneId;

    private VneId() {
        // Empty constructor required as of Neo4j API 2.0.5
    };

    public VneId(String vneId) {
        this.vneId = vneId;
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

    @Relationship(type = "LINK_TO", direction =  Relationship.OUTGOING)
    public Set<BrowserId> browserIds;
    public void linkToBrowserId(BrowserId browserId){
        if (browserIds == null) browserIds = new HashSet<>();
        browserIds.add(browserId);
    }

    @Relationship(type = "HAS_A", direction = Relationship.OUTGOING)
    public Email email;
    public void hasEmail(Email email) {
        this.email = email;
    }

    public String getVneId() {
        return vneId;
    }

    public void setVneId(int vioId) {
        this.vneId = vneId;
    }
}
