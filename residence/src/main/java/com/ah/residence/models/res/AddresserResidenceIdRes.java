package com.ah.residence.models.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * レスポンス用のIDを含むDTO
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AddresserResidenceIdRes {

	@JsonProperty("addressser_residence.id")
	private Integer addressserResidenceId;

	@JsonProperty("addressser_residence.consumer_id")
	private Integer consumerId;

	@JsonProperty("addressser_residence.addresser_id")
	private Integer addresserId;

	@JsonProperty("addressser_residence.address_id")
	private Integer addressId;

	@JsonProperty("addressser_residence.after_street")
	private String afterStreet;
}
