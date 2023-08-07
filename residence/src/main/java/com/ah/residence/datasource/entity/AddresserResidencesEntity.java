package com.ah.residence.datasource.entity;

import com.ah.residence.datasource.entity.parts.SystemDateEntityParts;
import jakarta.persistence.*;
import lombok.extern.apachecommons.CommonsLog;

@Entity
@Table(name = "addresser_residences")
public class AddresserResidencesEntity extends SystemDateEntityParts {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addresser_residences_addresser_residence_id_seq")
    @SequenceGenerator(name = "addresser_residences_addresser_residence_id_seq",
            sequenceName = "addresser_residences_addresser_residence_id_seq", allocationSize = 1)
    private int ApartResidenceId;

    @Column(name = "consumer_id", nullable = false)
    private Integer consumerId;

    @Column(name = "addresser_id", nullable = false)
    private Integer addresserId;

    @Column(name = "prefecture_code", nullable = false)
    private Integer prefectureCode;

    @Column(name = "city_code", nullable = false)
    private Integer cityCode;

    @Column(name = "after_street", nullable = false)
    private String afterStreet;
}
