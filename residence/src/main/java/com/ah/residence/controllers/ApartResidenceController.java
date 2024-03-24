package com.ah.residence.controllers;

import java.util.Objects;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ah.residence.exception.ResidenceException;
import com.ah.residence.message.ValidationMessageEnum;
import com.ah.residence.models.req.CommonReq;
import com.ah.residence.models.res.ApartResidenceRes;
import com.ah.residence.services.ApartResidenceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("apart_residence")
@RequiredArgsConstructor
public class ApartResidenceController {

	private final ApartResidenceService apartResidenceService;
	
	public final static String TABLE_NAME = "apart_residences";
	public final static String ENTITY_REL_FIELD_NAME = "apartResidences";
	public final static String PK_COLUMN_NAME = "apart_residence_id";
	public final static String PK_PROPERTY = TABLE_NAME + "." + PK_COLUMN_NAME;

	/**
	 * 登録用リクエストマッピング
	 * 
	 * @param reqBody CommonReq
	 * @return addresser_residence_id(主キー)のみ
	 */
	@PostMapping("regist")
	public ApartResidenceRes addApartResidences(@RequestBody CommonReq reqBody) {
		if(Objects.nonNull(reqBody.getId())) {
			throw new ResidenceException(ValidationMessageEnum.RequestNotRequiredIdError.getM());
		}
		return apartResidenceService.create(reqBody);
	}

	/**
	 * 更新用リクエストマッピング
	 * 
	 * @param reqBody CommonReq
	 * @return 登録後のデータ
	 * @throws ResidenceException
	 */
	@PutMapping("update")
	public ApartResidenceRes updateApartResidence(@RequestBody CommonReq reqBody){
		if(Objects.isNull(reqBody.getId())) {
			throw new ResidenceException(ValidationMessageEnum.RequestRequiredIdError.getM());
		}
		return apartResidenceService.update(reqBody);
	}

	/**
	 * 削除用リクエストマッピング
	 * 
	 * @param reqBody CommonReq
	 * @throws ResidenceException
	 */
	@DeleteMapping("delete")
	public void deleteApartResidence(@RequestBody CommonReq reqBody){
		if(Objects.isNull(reqBody.getId())) {
			throw new ResidenceException(ValidationMessageEnum.RequestRequiredIdError.getM());
		}
		apartResidenceService.delete(reqBody.getId());
	}
}
