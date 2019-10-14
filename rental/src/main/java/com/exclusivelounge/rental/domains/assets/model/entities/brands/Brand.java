package com.exclusivelounge.rental.domains.assets.model.entities.brands;

import com.exclusivelounge.rental.domains.assets.model.enums.AssetType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "BRAND")
public class Brand {

    public Brand() {
    }

    public Brand(Long id, String name, AssetType assetType) {
        this.id = id;
        this.name = name;
        this.assetType = assetType;
    }

    public Brand(String name, AssetType assetType) {
        this(null, name, assetType);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "BRAND_NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "ASSET_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private AssetType assetType;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "brand", orphanRemoval = true)
    private List<BrandModel> models = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        models.forEach(this::addModel);
    }

    public void addModel(BrandModel model) {
        models.add(model);
        model.setBrand(this);
    }

    public void removeModel(BrandModel model) {
        models.remove(model);
        model.setBrand(null);
    }



    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name=" + name +
                ", assetType=" + assetType +
                ", models=" + models +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brand brand = (Brand) o;
        return Objects.equals(id, brand.id) &&
                Objects.equals(name, brand.name) &&
                assetType == brand.assetType &&
                Objects.deepEquals(models, brand.models);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, assetType, models);
    }
}
