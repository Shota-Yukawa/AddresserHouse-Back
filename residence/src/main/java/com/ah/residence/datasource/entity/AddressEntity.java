package com.ah.residence.datasource.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class AddressEntity {

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_address_id_seq")
    @SequenceGenerator(name = "address_address_id_seq",
            sequenceName = "address_address_id_seq", allocationSize = 1)
    private Integer addressId;

    @Column(name = "prefecture_code")
    private Integer prefectureCode;

    @Column(name = "prefecture")
    private String prefecture;

    @Column(name = "prefecture_kana")
    private String prefectureKana;

    @Column(name = "prefecture_en")
    private String PrefectureEn;

    @Column(name = "city_code")
    private Integer cityCode;

    @Column(name = "city")
    private String city;

    @Column(name = "city_kana")
    private String cityKana;

    @Column(name = "city_en")
    private String CityEn;

    @Column(name = "street")
    private String street;

    @Column(name = "Street_kana")
    private String streetKana;

    @Column(name = "Street_en")
    private String streetEn;

    @Column(name = "landmark")
    private String landmark;

    @Column(name = "ido")
    private float ido;

    @Column(name = "keei")
    private float keei;

}
