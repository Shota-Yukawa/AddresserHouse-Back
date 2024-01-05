package com.ah.apartowner.models.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApartmentReq {

	private Integer apartmentId;

	@JsonProperty("apartments.apartowner_id")
	private Integer apartownerId;

	@JsonProperty("apartments.apart_name")
	private String apartName;

	@JsonProperty("apartments.apart_name_kana")
	private String apartNameKana;

	@JsonProperty("apartments.number_of_room")
	private int numOfRoom;

	@JsonProperty("apartments.number_of_floor")
	private int numOfFloor;

	@JsonProperty("apartments.build_on")
	private String buildOn;

	@JsonProperty("apartments.post_code")
	private String postCode;

	@JsonProperty("apartments.address_id")
	private Integer addressId;

	@JsonProperty("apartments.after_street")
	private String afterStreet;

}
