package com.ah.apartowner.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "apart_news_range")
@Data
@EqualsAndHashCode(callSuper = false)
public class ApartNewsRangeEntity {

	@Id
	@Column(name = "apart_news_range_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer AraprtNewsRangeId;

	@ManyToOne
	@JoinColumn(name = "apart_news_id", referencedColumnName = "apart_news_id", nullable = false)
	private ApartNewsEntity apartNews;

	@ManyToOne
	@JoinColumn(name = "apartment_id", referencedColumnName = "apartment_id", nullable = false)
	private ApartmentsEntity apartment;

	@Column(name = "is_all", nullable = false)
	private boolean isAll;

	@ManyToOne
	@JoinColumn(name = "apart_room_id", referencedColumnName = "apart_room_id")
	private ApartRoomsEntity apartRoom;

}
