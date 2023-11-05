package com.ah.apartowner.models.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApartownerRes {

	@JsonProperty("apartowner.apartowner_id")
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
	
	@JsonProperty("apartowner.regist_at")
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss.SSS")
	private LocalDateTime registAt;
	
	@JsonProperty("apartowner.update_at")
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss.SSS")
	private LocalDateTime updateAt;
}
