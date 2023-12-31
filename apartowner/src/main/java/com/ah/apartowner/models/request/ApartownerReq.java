package com.ah.apartowner.models.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * リクエスト用DTO（IDを除く）
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown=true)
public class ApartownerReq {
	
	private Integer apartownerId;

	@JsonProperty("apartowners.apartowner_name")
	private String apartownerName;

	@JsonProperty("apartowners.apartowner_name_kane")
	private String apartownerNameKana;

	@JsonProperty("apartowners.email")
	private String email;

	@JsonProperty("apartowners.phone_number")
	private String phoneNumber;

	@JsonProperty("apartowners.post_code")
	private String postCode;

	@JsonProperty("apartowners.address_id")
	private Integer addressId;

	@JsonProperty("apartowners.after_street")
	private String afterStreet;
}
