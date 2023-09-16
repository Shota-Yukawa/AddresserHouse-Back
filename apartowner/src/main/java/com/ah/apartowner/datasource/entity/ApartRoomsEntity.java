package com.ah.apartowner.datasource.entity;

import java.util.List;

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
@Table(name = "apart_rooms")
@Data
@EqualsAndHashCode(callSuper = false)
@SequenceGenerator(name = "pkey_seq", sequenceName = "apart_rooms_apart_room_id_seq", allocationSize = 1)
public class ApartRoomsEntity {

	@Id
	@Column(name = "apart_room_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pkey_seq")
	private Integer apartRoomId;

	@ManyToOne
	@JoinColumn(name = "apartment_id", referencedColumnName = "apartment_id", nullable = false)
	private ApartmentsEntity apartment;

	@Column(name = "room_name", nullable = false)
	private String roomName;

	@Column(name = "room_kana")
	private String roomKana;

	@Column(name = "floor", nullable = false)
	private int floor;

	// リレーションマッピング
	@OneToMany(mappedBy = "apartRoom")
	private List<ApartNewsRangeEntity> apartNewsRanges;
}
