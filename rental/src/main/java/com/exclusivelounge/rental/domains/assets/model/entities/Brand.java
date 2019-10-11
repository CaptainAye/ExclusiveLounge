package com.exclusivelounge.rental.domains.assets.model.entities;

import com.exclusivelounge.rental.domains.assets.model.enums.AssetType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "BRAND")
public class Brand {

    public Brand() {
    }

    public Brand(String name, AssetType assetType) {
        this.name = name;
        this.assetType = assetType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(name = "BRAND_NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "ASSET_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private AssetType assetType;

    @OneToMany
    private List<BrandModel> models;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AssetType getAssetType() {
        return assetType;
    }

    public void setAssetType(AssetType assetType) {
        this.assetType = assetType;
    }

    public List<BrandModel> getModels() {
        return models;
    }

    public void setModels(List<BrandModel> models) {
        this.models = models;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", assetType=" + assetType +
                ", models=" + models +
                '}';
    }
}