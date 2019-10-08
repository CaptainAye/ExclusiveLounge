package com.exclusivelounge.rental.domains.assets.entities;

import javax.persistence.*;

@Entity
@Table(name = "BRAND_MODEL")
public class BrandModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "MODEL_NAME", nullable = false)
    private String modelName;

    @Column(name = "YEAR_STARTED", nullable = false)
    private long yearStarted;

    @Column(name = "YEAR_ENDED")
    private long yearEnded;

    @ManyToOne
    @JoinColumn(name = "BRAND_ID", referencedColumnName = "ID", nullable = false)
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

    public void setYearStarted(long yearStarted) {
        this.yearStarted = yearStarted;
    }

    public long getYearEnded() {
        return yearEnded;
    }

    public void setYearEnded(long yearEnded) {
        this.yearEnded = yearEnded;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
