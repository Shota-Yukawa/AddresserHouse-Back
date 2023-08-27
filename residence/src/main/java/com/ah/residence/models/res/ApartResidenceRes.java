package com.ah.residence.models.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApartResidenceRes {

	@JsonProperty("apart_residence.apart_residence_id")
	private Integer apartResidienceId;

	@JsonProperty("apart_residence.consumer_id")
	private Integer consumerId;

	@JsonProperty("apart_residence.apartment_id")
	private Integer apartmentId;

	@JsonProperty("apart_residence.apart_room_id")
	private Integer apartRoomId;
}
