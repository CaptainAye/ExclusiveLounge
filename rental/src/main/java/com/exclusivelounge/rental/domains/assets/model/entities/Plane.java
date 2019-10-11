package com.exclusivelounge.rental.domains.assets.model.entities;

import static com.exclusivelounge.rental.domains.assets.model.enums.AssetType.*;

import javax.persistence.*;

@Entity(name="assets")
@DiscriminatorValue(AssetTypeValues.PLANE)
public class Plane extends Vehicle {
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "BRAND_MODEL_ID", referencedColumnName = "ID")
    })
    private BrandModel brandModel;

}
