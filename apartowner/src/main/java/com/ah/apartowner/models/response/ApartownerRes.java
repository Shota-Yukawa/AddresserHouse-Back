package com.ah.apartowner.models.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApartownerRes {

	@JsonProperty("apartowners.apartowner_id")
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
	
	@JsonProperty("apartowners.regist_at")
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss.SSS")
	private LocalDateTime registAt;
	
	@JsonProperty("apartowners.update_at")
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss.SSS")
	private LocalDateTime updateAt;
}
