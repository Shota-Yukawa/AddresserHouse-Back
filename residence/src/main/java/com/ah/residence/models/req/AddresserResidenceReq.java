package com.ah.residence.models.req;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * リクエスト用DTO（IDを除く）
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AddresserResidenceReq {

	@JsonProperty("addresser_residences.consumer_id")
	private Integer consumerId;

	@JsonProperty("addresser_residences.addresser_id")
	private Integer addresserId;

	@JsonProperty("addresser_residences.address_id")
	private Integer addressId;

	@JsonProperty("addresser_residences.after_streat")
	private String afterStreet;
}
