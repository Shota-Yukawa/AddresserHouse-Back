package com.ah.apartowner.models.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApartmentRes {

	@JsonProperty("apartments.apartment_id")
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
	@JsonFormat(pattern = "yyyy/MM/dd")
	private LocalDate buildOn;


	@JsonProperty("apartments.post_code")
	private String postCode;


	@JsonProperty("apartments.address_id")
	private Integer addressId;


	@JsonProperty("apartments.after_street")
	private String afterStreet;
	
	@JsonProperty("apartments.regist_at")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	private LocalDateTime registAt;
	
	@JsonProperty("apartments.update_at")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	private LocalDateTime updateAt;
}
