package com.exclusivelounge.rental.domains.assets.model.entities;

import com.exclusivelounge.rental.domains.assets.model.enums.AssetType;
import com.exclusivelounge.rental.model.enumerations.RentalState;

import static com.exclusivelounge.rental.domains.assets.model.enums.AssetType.*;

import javax.persistence.*;

@Entity(name="assets")
@DiscriminatorValue(AssetTypeValues.CAR)
public class Car extends Vehicle{

    public Car() {
        super();
    }

    public Car(BrandModel brandModel, CarCharacteristics carCharacteristics, RentalState rentalState) {
        super(CAR, brandModel, rentalState);
        this.carCharacteristics = carCharacteristics;
    }

    @Embedded
    private CarCharacteristics carCharacteristics;

    public CarCharacteristics getCarCharacteristics() {
        return carCharacteristics;
    }

    public void setCarCharacteristics(CarCharacteristics carCharacteristics) {
        this.carCharacteristics = carCharacteristics;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carCharacteristics=" + carCharacteristics +
                ", brandModel=" + brandModel +
                ", id=" + id +
                ", assetType=" + assetType +
                ", rentalState=" + rentalState +
                '}';
    }
}
