package com.exclusivelounge.rental.domains.assets.model.entities;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.List;

@Embeddable
public class CarCharacteristics {
    private int madeYear;
    private int horsePower;
    private String engineCapacity;
    @ElementCollection
    private List<String> equipment;

    public int getMadeYear() {
        return madeYear;
    }

    public void setMadeYear(int madeYear) {
        this.madeYear = madeYear;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public List<String> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<String> equipment) {
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return "CarCharacteristics{" +
                "madeYear=" + madeYear +
                ", horsePower=" + horsePower +
                ", engineCapacity='" + engineCapacity + '\'' +
                ", equipment=" + equipment +
                '}';
    }
}
