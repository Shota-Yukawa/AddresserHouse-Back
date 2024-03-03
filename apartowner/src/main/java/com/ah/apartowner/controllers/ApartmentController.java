package com.ah.apartowner.controllers;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ah.apartowner.exception.AapartownerException;
import com.ah.apartowner.messages.ValidationMessageEnum;
import com.ah.apartowner.models.request.CommonReq;
import com.ah.apartowner.models.response.ApartmentRes;
import com.ah.apartowner.services.ApartmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("apartment")
public class ApartmentController {

	private final ApartmentService apartmentService;
	
	public final static String TABLE_NAME = "apartments";
	public final static String ENTITY_REL_FIELD_NAME = "apartment";
	public final static String PK_COLUMN_NAME = "apartment_id";
	public final static String PK_PROPERTY = TABLE_NAME + "." + PK_COLUMN_NAME;
	
	/**
	 * ~/apartment/regist<br>
	 * apartmentの登録リクエスト取得
	 * @param reqBody
	 * @return
	 */
	@PostMapping("regist")
	public ApartmentRes registApartment(@RequestBody CommonReq reqBody) {
		if (Objects.nonNull(reqBody.getId())) {
			throw new AapartownerException(ValidationMessageEnum.RequestNotRequiredIdError.getM());
		}
		return apartmentService.regist(reqBody);
	}
	
	/**
	 * ~/apartments/update<br>
	 * apartmentの更新リクエスト
	 * @param reqBody
	 * @return
	 */
	@PutMapping("update")
	public ApartmentRes updateApartment(@RequestBody CommonReq reqBody) {
		if(Objects.isNull(reqBody.getId())) {
			throw new AapartownerException(ValidationMessageEnum.RequestRequiredIdError.getM());
		}
		return apartmentService.update(reqBody);
	}
	
	/**
	 * ~/apartments/update/parts<br>
	 * ppartmwntの部分項目更新リクエスト
	 * @param reqBody
	 * @return
	 */
	@PutMapping("update/parts")
	public List<Map<String, Object>> updatePartsApartment(@RequestBody List<Map<String, Object>> reqBody) {
		if (!reqBody.stream().allMatch(reqData->reqData.containsKey(PK_PROPERTY) && Objects.nonNull(reqData.get(PK_PROPERTY)))) {
			throw new AapartownerException(ValidationMessageEnum.RequestRequiredIdError.getM());
		}
		return apartmentService.updatePart(reqBody);
	}
	
	public ApartmentRes deleteApartment(@RequestBody CommonReq reqBody) {
		if(Objects.isNull(reqBody.getId())) {
			throw new AapartownerException(ValidationMessageEnum.RequestRequiredIdError.getM());
		}
		return apartmentService.delete(reqBody.getId());
	}
}
