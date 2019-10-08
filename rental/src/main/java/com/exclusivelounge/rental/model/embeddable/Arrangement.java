package com.exclusivelounge.rental.model.embeddable;

import com.exclusivelounge.rental.model.entities.Location;

import javax.persistence.*;
import java.time.LocalDateTime;

@Embeddable
public class Arrangement {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    //@JoinColumn(name="location_id", referencedColumnName = "id")
    private Location location;

    @Column(name = "meeting_date", nullable = false)
    private LocalDateTime meetingDate;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDateTime getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(LocalDateTime meetingDate) {
        this.meetingDate = meetingDate;
    }
}
