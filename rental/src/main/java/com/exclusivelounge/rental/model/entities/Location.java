package com.exclusivelounge.rental.model.entities;

import com.exclusivelounge.rental.model.embeddable.Address;
import com.exclusivelounge.rental.domains.assets.model.enums.AssetType;

import javax.persistence.*;
import java.util.Set;

@Table(name = "LOCATION")
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private Address address;

    @ElementCollection
    @CollectionTable(name = "ASSET_TYPE", joinColumns = @JoinColumn(name = "LOCATION_ID"))
    private Set<AssetType> handledAssetTypes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
