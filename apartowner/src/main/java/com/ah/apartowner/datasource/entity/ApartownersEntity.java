package com.ah.apartowner.datasource.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "apartowner")
@Data
@EqualsAndHashCode(callSuper = false)
public class ApartownersEntity {

	@Id
	@Column(name = "apartowner_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer apartownerId;

	@Column(name = "apartowner_name", nullable = false)
	private String apartownerName;

	@Column(name = "apartowner_name_kana")
	private String apartownerNameKana;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;

	@Column(name = "max_user", nullable = false)
	private int macuser = 5;

	@Column(name = "post_code")
	private String postCode;

	@Column(name = "address_id", nullable = false)
	private Integer addressId;

	@Column(name = "after_street")
	private String afterStreet;

	// リレーションマッピング
	@OneToMany(mappedBy = "apartowner")
	private List<ApartmentsEntity> apartments;

	@OneToOne(mappedBy = "apartowner")
	private ApartownerContactsEntity apartownerContact;
}
