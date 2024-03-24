package com.ah.residence.datasource.entity;

import com.ah.residence.datasource.entity.parts.SystemDateEntityParts;
import com.ah.residence.lintener.tablesync.AddresserResidenceListener;

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
@Table(name = "addresser_residences")
@Data
@EqualsAndHashCode(callSuper = false)
@SequenceGenerator(name = "pkey_seq", sequenceName = "addresser_residences_addresser_residence_id_seq", allocationSize = 1)
@EntityListeners(AddresserResidenceListener.class)
public class AddresserResidencesEntity extends SystemDateEntityParts {

	@Id
	@Column(name = "addresser_residence_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pkey_seq")
	private Integer addresserResidenceId;

	@Column(name = "consumer_id", nullable = false)
	private Integer consumerId;

	@Column(name = "addresser_id", nullable = false)
	private Integer addresserId;

	@Column(name = "address_id", nullable = false)
	private Integer AddressId;

	@Column(name = "after_street")
	private String afterStreet;
}
