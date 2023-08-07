package com.ah.residence.datasource.entity;

import com.ah.residence.datasource.entity.parts.SystemDateEntityParts;
import jakarta.persistence.*;


@Entity
@Table(name = "apart_residences")
public class ApartResidencesEntity extends SystemDateEntityParts {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apart_residences_apart_residence_id_seq")
    @SequenceGenerator(name = "apart_residences_apart_residence_id_seq",
            sequenceName = "apart_residences_apart_residence_id_seq", allocationSize = 1)
    private int ApartResidenceId;

    @Column(name = "consumer_id", nullable = false)
    private Integer consumerId;

    @Column(name = "apartment_id", nullable = false)
    private Integer apartmentId;

    @Column(name = "apart_room_id")
    private Integer roomNum;

}
