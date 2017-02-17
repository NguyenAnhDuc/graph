package com.example.models.nodes;

/**
 * Created by ducna on 2/6/2017.
 */
import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class FptId {

    @GraphId private Long id;

    @Index(unique = true)
    private String fptId;

    private FptId() {
        // Empty constructor required as of Neo4j API 2.0.5
    };

    public FptId(String fptId) {
        this.fptId = fptId;
    }

    /**
     * Neo4j doesn't REALLY have bi-directional relationships. It just means when querying
     * to ignore the direction of the relationship.
     * https://dzone.com/articles/modelling-data-neo4j
     */
    @Relationship(type = "LINK_TO", direction =  Relationship.OUTGOING)
    public Set<VioId> vioIds;
    public void linkToVioId(VioId vioId){
        if (vioIds == null){
            vioIds = new HashSet<>();
        }
        vioIds.add(vioId);
    }

    @Relationship(type = "LINK_TO", direction =  Relationship.OUTGOING)
    public Set<VneId> vneIds;
    public void linkToVneId(VneId vneId){
        if (vneIds == null){
            vneIds = new HashSet<>();
        }
        vneIds.add(vneId);
    }

    @Relationship(type = "LINK_TO", direction =  Relationship.OUTGOING)
    public Set<BrowserId> browserIds;
    public void linkToBrowserId(BrowserId browserId){
        if (browserIds == null){
            browserIds = new HashSet<>();
        }
        browserIds.add(browserId);
    }

    @Relationship(type = "HAS_A", direction = Relationship.OUTGOING)
    public Set<Email> emails;

    public void hasEmail(Email email) {
        if (emails == null) {
            emails = new HashSet<>();
        }
        emails.add(email);
    }

    @Relationship(type = "HAS_A", direction = Relationship.OUTGOING)
    public Set<Phone> phones;

    public void hasPhone(Phone phone) {
        if (phones == null) {
            phones = new HashSet<>();
        }
        phones.add(phone);
    }

    @Relationship(type = "HAS_A", direction = Relationship.OUTGOING)
    public Set<Province> provinces;
    public void hasProvince(Province province) {
        if (provinces == null) {
            provinces = new HashSet<>();
        }
        provinces.add(province);
    }

    @Relationship(type = "HAS_A", direction = Relationship.OUTGOING)
    public Set<District> districts;
    public void hasDistrict(District district) {
        if (districts == null) {
            districts = new HashSet<>();
        }
        districts.add(district);
    }

    @Relationship(type = "HAS_A", direction = Relationship.OUTGOING)
    public Set<Address> addresses;
    public void hasAddress(Address address){
        if (addresses == null) {
            addresses = new HashSet<>();
        }
        addresses.add(address);
    }

    public String getFptId() {
        return fptId;
    }

    public void setFptId(String fptId) {
        this.fptId = fptId;
    }
}