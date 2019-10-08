package com.exclusivelounge.rental.model.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GpsPosition {
    @Column(name = "current_position_lat")
    private Double latitude;
    @Column(name = "current_position_lon")
    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
