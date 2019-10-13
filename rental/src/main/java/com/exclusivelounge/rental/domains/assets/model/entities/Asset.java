package com.exclusivelounge.rental.domains.assets.model.entities;

import com.exclusivelounge.rental.domains.assets.model.enums.AssetType;
import com.exclusivelounge.rental.model.enumerations.RentalState;

import javax.persistence.*;

@Entity
@Table(name = "ASSETS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ASSET_TYPE", discriminatorType = DiscriminatorType.STRING)
public abstract class Asset {

    public Asset() {
    }

    public Asset( AssetType assetType, RentalState rentalState) {
        this.assetType = assetType;
        this.rentalState = rentalState;
    }

    public Asset(RentalState rentalState) {
        this.rentalState = rentalState;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;

    @Column(name = "ASSET_TYPE", updatable = false, insertable = false)
    @Enumerated(EnumType.STRING)
    protected AssetType assetType;

    @Enumerated(EnumType.STRING)
    protected RentalState rentalState;

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

    public abstract String getName();

    @Override
    public String toString() {
        return "Asset{" +
                "id=" + id +
                ", assetType=" + assetType +
                ", rentalState=" + rentalState +
                '}';
    }
}
