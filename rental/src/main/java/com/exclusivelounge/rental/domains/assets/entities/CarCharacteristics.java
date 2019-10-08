package com.exclusivelounge.rental.domains.assets.entities;

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
}
