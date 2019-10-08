package com.exclusivelounge.rental.domains.assets.entities;

import static com.exclusivelounge.rental.domains.assets.enums.AssetType.*;

import javax.persistence.*;

@Entity(name = "assets")
@DiscriminatorValue(AssetTypeValues.YACHT)
public class Yacht extends Vehicle {
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "BRAND_MODEL_ID", referencedColumnName = "ID")
    })
    private BrandModel brandModel;

}
