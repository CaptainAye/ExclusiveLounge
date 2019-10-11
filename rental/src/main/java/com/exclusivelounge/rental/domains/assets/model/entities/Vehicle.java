package com.exclusivelounge.rental.domains.assets.model.entities;

import com.exclusivelounge.rental.domains.assets.model.enums.AssetType;
import com.exclusivelounge.rental.model.enumerations.RentalState;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity
public abstract class Vehicle extends Asset{

    public Vehicle() {
        super();
    }

    public Vehicle( AssetType assetType, BrandModel brandModel, RentalState rentalState) {
        super(assetType, rentalState);
        this.brandModel = brandModel;
    }

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "BRAND_MODEL_ID", referencedColumnName = "ID")
    })
    protected BrandModel brandModel;

    public BrandModel getBrandModel() {
        return brandModel;
    }

    public void setBrandModel(BrandModel brandModel) {
        this.brandModel = brandModel;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "brandModel=" + brandModel +
                ", id=" + id +
                ", assetType=" + assetType +
                ", rentalState=" + rentalState +
                '}';
    }
}
