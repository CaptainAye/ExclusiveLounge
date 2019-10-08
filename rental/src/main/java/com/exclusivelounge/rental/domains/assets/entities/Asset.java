package com.exclusivelounge.rental.domains.assets.entities;

import com.exclusivelounge.rental.domains.assets.enums.AssetType;
import com.exclusivelounge.rental.model.enumerations.RentalState;

import javax.persistence.*;

@Entity
@Table(name = "ASSETS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ASSET_TYPE", discriminatorType = DiscriminatorType.STRING)
public abstract class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "ASSET_TYPE", updatable = false, insertable = false)
    private AssetType assetType;

    private RentalState rentalState;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AssetType getAssetType() {
        return assetType;
    }

    public RentalState getRentalState() {
        return rentalState;
    }

    public void setRentalState(RentalState rentalState) {
        this.rentalState = rentalState;
    }
}
