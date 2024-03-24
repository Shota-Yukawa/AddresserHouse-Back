package com.ah.residence.models.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ApartResidenceReq {

	@JsonProperty("apart_residences.consumer_id")
	private Integer consumerId;

	@JsonProperty("apart_residences.apartment_id")
	private Integer apartmentId;

	@JsonProperty("apart_residences.apart_room_id")
	private Integer roomId;
}
