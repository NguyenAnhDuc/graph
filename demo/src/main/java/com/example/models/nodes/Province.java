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
public class Province {

    @GraphId
    private Long id;

    @Index(unique = true)
    private String province;

    private Province() {
        // Empty constructor required as of Neo4j API 2.0.5
    };

    public Province(String province) {
        this.province = province;
    }

    /*@Relationship(type = "HAS_A", direction = Relationship.INCOMING)
    public Set<FptId> fptIds;
    public void hasFptId(FptId fptId){
        if (fptIds == null) fptIds = new HashSet<>();
        fptIds.add(fptId);
    }*/

    @Relationship(type = "BELONGS_TO", direction = Relationship.OUTGOING)
    public Set<District> districts;
    public void belongToDistrict(District district){
        if (this.districts == null) this.districts = new HashSet<>();
        this.districts.add(district);
    }

    public String getProvince() {
        return province;
    }

    public void setName(String province) {
        this.province = province;
    }
}
