package com.exclusivelounge.rental.domains.assets.model.entities;

import com.exclusivelounge.rental.domains.assets.model.entities.brands.BrandModel;
import com.exclusivelounge.rental.model.enumerations.RentalState;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity
public abstract class Vehicle extends Asset{

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "BRAND_MODEL_ID", referencedColumnName = "ID")
    })
    protected BrandModel brandModel;

    public Vehicle() {
        super();
    }

    public Vehicle(BrandModel brandModel, RentalState rentalState) {
        super(rentalState);
        this.brandModel = brandModel;
    }

    public BrandModel getBrandModel() {
        return brandModel;
    }

    public void setBrandModel(BrandModel brandModel) {
        this.brandModel = brandModel;
    }

    @Override
    public String getName() {
        return brandModel.getName();
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
