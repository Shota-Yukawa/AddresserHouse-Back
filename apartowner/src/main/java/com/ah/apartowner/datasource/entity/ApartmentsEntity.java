package com.ah.apartowner.datasource.entity;

import java.time.LocalDate;
import java.util.List;

import com.ah.apartowner.datasource.entity.parts.SystemDateEntityParts;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "apartments")
@Data
@EqualsAndHashCode(callSuper = false)
@SequenceGenerator(name = "pkey_seq", sequenceName = "apartments_apartment_id_seq", allocationSize = 1)
public class ApartmentsEntity extends SystemDateEntityParts {

	@Id
	@Column(name = "apartment_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pkey_seq")
	private Integer apartmentsId;

	@ManyToOne
	@JoinColumn(name = "apartowner_id", nullable = false, referencedColumnName = "apartowner_id")
	private ApartownersEntity apartowner;

	@Column(name = "apart_name", nullable = false)
	private String apartName;

	@Column(name = "apart_name_kana")
	private String apartNameKana;

	@Column(name = "number_of_room")
	private int numOfRoom;

	@Column(name = "number_of_floot")
	private int numOfFloor;

	@Column(name = "build_on")
	private LocalDate buildOn;

	@Column(name = "post_code")
	private String postCode;

	@Column(name = "adrresss_id", nullable = false)
	private Integer addressId;

	@Column(name = "after_street")
	private String afterStreet;

	// リレーションマッピング
	@OneToMany(mappedBy = "apartment")
	private List<ApartRoomsEntity> apartRooms;

	@OneToMany(mappedBy = "apartment")
	private List<ApartNewsRangeEntity> apartNewsRanges;
}
