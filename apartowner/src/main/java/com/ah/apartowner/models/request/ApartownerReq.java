package com.ah.apartowner.models.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * リクエスト用DTO（IDを除く）
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApartownerReq {
	
	private Integer apartownerId;

	@JsonProperty("apartowner.apartowner_name")
	private String apartownerName;

	@JsonProperty("apartowner.apartowner_name_kane")
	private String apartownerNameKana;

	@JsonProperty("apartowner.email")
	private String email;

	@JsonProperty("apartowner.phone_number")
	private String phoneNumber;

	@JsonProperty("apartowner.post_code")
	private String postCode;

	@JsonProperty("apartowner.address_id")
	private Integer addressId;

	@JsonProperty("apartowner.after_street")
	private String afterStreet;
}
