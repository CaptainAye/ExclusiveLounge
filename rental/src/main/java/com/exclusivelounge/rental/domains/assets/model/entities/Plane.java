package com.exclusivelounge.rental.domains.assets.model.entities;

import com.exclusivelounge.rental.domains.assets.model.entities.brands.BrandModel;
import com.exclusivelounge.rental.model.enumerations.RentalState;

import static com.exclusivelounge.rental.domains.assets.model.enums.AssetType.*;

import javax.persistence.*;

@Entity(name="assets")
@DiscriminatorValue(AssetTypeValues.PLANE)
public class Plane extends Vehicle {

    public Plane() {
    }

    public Plane(BrandModel brandModel, RentalState rentalState) {
        super(brandModel, rentalState);
    }
}
