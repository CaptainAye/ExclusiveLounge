package com.exclusivelounge.rental.domains.assets.entities;

import com.exclusivelounge.rental.validation.contraints.AssetTypeMatch;

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
    @AssetTypeMatch
    private BrandModel brandModel;

    public BrandModel getBrandModel() {
        return brandModel;
    }

    public void setBrandModel(BrandModel brandModel) {
        this.brandModel = brandModel;
    }
}
