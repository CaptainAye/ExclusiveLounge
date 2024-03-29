package com.exclusivelounge.rental.domains.assets.model.entities.brands;

import com.exclusivelounge.rental.validation.annotations.IsValidYear;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BRAND_MODEL")
public class BrandModel {

    public BrandModel() {
    }

    public BrandModel( String modelName, Integer yearStarted, Integer yearEnded) {
        this(null, modelName, yearStarted, yearEnded);
    }

    public BrandModel( String modelName, Integer yearStarted) {
        this(modelName, yearStarted, null);
    }

    public BrandModel(Long id, String modelName, Integer yearStarted, Integer yearEnded) {
        this.modelName = modelName;
        this.yearStarted = yearStarted;
        this.yearEnded = yearEnded;
        this.id = id;
    }

    public BrandModel(Long id, String modelName, Integer yearStarted) {
        this(id, modelName, yearStarted, null);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MODEL_NAME", nullable = false)
    private String modelName;

    @Column(name = "YEAR_STARTED", nullable = false)
    @IsValidYear
    private Integer yearStarted;

    @Column(name = "YEAR_ENDED")
    @IsValidYear
    private Integer yearEnded;

    @ManyToOne
    @JoinColumn(name = "BRAND_ID", referencedColumnName = "ID", nullable = false, updatable = false)
    private Brand brand;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public long getYearStarted() {
        return yearStarted;
    }

    public void setYearStarted(Integer yearStarted) {
        this.yearStarted = yearStarted;
    }

    public long getYearEnded() {
        return yearEnded;
    }

    public void setYearEnded(Integer yearEnded) {
        this.yearEnded = yearEnded;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getName() {
        return brand.getName() + modelName;
    }

    @Override
    public String toString() {
        return "BrandModel{" +
                "id=" + id +
                ", name='" + getName() +
                ", yearStarted=" + yearStarted +
                ", yearEnded=" + yearEnded +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrandModel that = (BrandModel) o;
        return id == that.id &&
                Objects.equals(modelName, that.modelName) &&
                Objects.equals(yearStarted, that.yearStarted) &&
                Objects.equals(yearEnded, that.yearEnded) &&
                Objects.equals(brand, that.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, modelName, yearStarted, yearEnded, brand);
    }
}
