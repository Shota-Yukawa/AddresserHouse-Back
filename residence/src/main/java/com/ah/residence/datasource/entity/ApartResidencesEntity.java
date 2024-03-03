package com.ah.residence.datasource.entity;

import com.ah.residence.datasource.entity.parts.SystemDateEntityParts;
import com.ah.residence.lintener.tablesync.ApartResidenceListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "apart_residences")
@Data
@EqualsAndHashCode(callSuper = false)
@SequenceGenerator(name = "pkey_seq", sequenceName = "apart_residences_apart_residence_id_seq", allocationSize = 1)
@EntityListeners(ApartResidenceListener.class)
public class ApartResidencesEntity extends SystemDateEntityParts {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pkey_seq")
	private Integer apartResidenceId;

	@Column(name = "consumer_id", nullable = false)
	private Integer consumerId;

	@Column(name = "apartment_id", nullable = false)
	private Integer apartmentId;

	@Column(name = "apart_room_id")
	private Integer roomNum;

}
