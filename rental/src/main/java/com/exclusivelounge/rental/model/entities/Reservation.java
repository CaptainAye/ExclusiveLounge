package com.exclusivelounge.rental.model.entities;

import com.exclusivelounge.rental.model.embeddable.Arrangement;
import com.exclusivelounge.rental.domains.assets.model.entities.Asset;

import javax.persistence.*;

@Entity
@Table(name = "RESERVATION")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Embedded
    @AssociationOverride(name = "location", joinColumns = @JoinColumn(name = "collect_location_id"))
    @AttributeOverride(name = "meetingDate", column = @Column(name = "collect_date"))
    private Arrangement collectArrangement;

    @Embedded
    @AssociationOverride(name = "location", joinColumns = @JoinColumn(name = "return_location_id"))
    @AttributeOverride(name = "meetingDate", column = @Column(name = "return_date"))
    private Arrangement returnArrangement;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Asset reservedAsset;
}
