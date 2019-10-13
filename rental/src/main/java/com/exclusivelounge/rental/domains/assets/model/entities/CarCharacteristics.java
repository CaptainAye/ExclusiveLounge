package com.exclusivelounge.rental.domains.assets.model.entities;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.List;

@Embeddable
public class CarCharacteristics {
    private Integer madeYear;
    private Integer horsePower;
    private String engineCapacity;
    @ElementCollection
    private List<String> equipment;

    public Integer getMadeYear() {
        return madeYear;
    }

    public void setMadeYear(Integer madeYear) {
        this.madeYear = madeYear;
    }

    public Integer getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(Integer horsePower) {
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
