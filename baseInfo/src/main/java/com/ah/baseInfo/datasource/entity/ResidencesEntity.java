package com.ah.baseInfo.datasource.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "residences")
public class ResidencesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "residences_residence_id_seq")
    @SequenceGenerator(name = "residences_residence_id_seq",
            sequenceName = "residences_residence_id_seq", allocationSize = 1)
    private int residenceIid;

    @Column(name = "consumer_id")
    private Integer consumerId;

    @Column(name = "reside_kind")
    private char resideKind;

    @Column(name = "apartment_id")
    private Integer apartmentId;

    @Column(name = "room_num")
    private Integer roomNum;

    @Column(name = "addresser_id")
    private Integer addresserId;

}
