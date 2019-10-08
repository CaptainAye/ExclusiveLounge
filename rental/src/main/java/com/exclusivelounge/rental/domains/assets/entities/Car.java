package com.exclusivelounge.rental.domains.assets.entities;

import static com.exclusivelounge.rental.domains.assets.enums.AssetType.*;

import javax.persistence.*;

@Entity(name="assets")
@DiscriminatorValue(AssetTypeValues.CAR)
public class Car extends Vehicle{

    @Embedded
    private CarCharacteristics carCharacteristics;

    public CarCharacteristics getCarCharacteristics() {
        return carCharacteristics;
    }

    public void setCarCharacteristics(CarCharacteristics carCharacteristics) {
        this.carCharacteristics = carCharacteristics;
    }
}
