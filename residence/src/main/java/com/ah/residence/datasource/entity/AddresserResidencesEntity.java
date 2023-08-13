package com.ah.residence.datasource.entity;

import com.ah.residence.datasource.entity.parts.SystemDateEntityParts;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class AddresserResidencesEntity extends SystemDateEntityParts {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addresser_residences_addresser_residence_id_seq")
	@SequenceGenerator(name = "addresser_residences_addresser_residence_id_seq", sequenceName = "addresser_residences_addresser_residence_id_seq", allocationSize = 1)
	private int AddresserResidenceId;

	@Column(name = "consumer_id", nullable = false)
	private Integer consumerId;

	@Column(name = "addresser_id", nullable = false)
	private Integer addresserId;

	@Column(name = "address_id", nullable = false)
	private Integer AddressId;

	@Column(name = "after_street")
	private String afterStreet;
}
