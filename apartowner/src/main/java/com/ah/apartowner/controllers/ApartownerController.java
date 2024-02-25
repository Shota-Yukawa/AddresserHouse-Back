package com.ah.apartowner.controllers;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ah.apartowner.exception.AapartownerException;
import com.ah.apartowner.messages.ValidationMessageEnum;
import com.ah.apartowner.models.request.CommonReq;
import com.ah.apartowner.models.response.ApartownerRes;
import com.ah.apartowner.services.ApartownerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("apartowner")
public class ApartownerController {

	private final ApartownerService apartownerService;
	
	public final static String TABLE_NAME = "apartowners";
	public final static String ENTITY_REL_FIELD_NAME = "apartowner";
	public final static String PK_COLUMN_NAME = "apartowner_id";
	public final static String PK_PROPERTY = TABLE_NAME + "." + PK_COLUMN_NAME;

	/**
	 * ~/apartowner/regist<br>
	 * apartownerの登録リクエスト取得
	 * @param reqBody
	 * @return
	 */
	@PostMapping("regist")
	public ApartownerRes registApartowner(@RequestBody CommonReq reqBody) {
		if (Objects.nonNull(reqBody.getId())) {
			throw new AapartownerException(ValidationMessageEnum.RequestNotRequiredIdError.getM());
		}
		return apartownerService.regist(reqBody);
	}
	
	/**
	 * ~/apartowner/update<br>
	 * apartownerの更新リクエスト取得
	 * @param reqBody
	 * @return
	 */
	@PutMapping("update")
	public ApartownerRes updateApartowner(@RequestBody CommonReq reqBody) {
		if(Objects.isNull(reqBody.getId())) {
			throw new AapartownerException(ValidationMessageEnum.RequestRequiredIdError.getM());
		}
		return apartownerService.update(reqBody);
	}

	/**
	 * ~/apartowner/update/parts<br>
	 * apartownerの部分項目更新リクエスト取得
	 * @param reqBody
	 * @return
	 */
	@PutMapping("update/parts")
	public List<Map<String, Object>> updatePartsApartowner(@RequestBody List<Map<String, Object>> reqBody) {
		if(!reqBody.stream().allMatch(reqData->reqData.containsKey(PK_PROPERTY) && Objects.nonNull(reqData.get(PK_PROPERTY)))) {
			throw new AapartownerException(ValidationMessageEnum.RequestRequiredIdError.getM());
		}
		return apartownerService.updatePart(reqBody);
	}
	
	/**
	 * ~/apartowner/delete<br>
	 * apartownerの削除リクエスト取得<
	 * @param reqBody
	 * @return
	 */
	@DeleteMapping("delete")
	public ApartownerRes deleteApartowner(@RequestBody CommonReq reqBody) {
		if (Objects.isNull(reqBody.getId())) {
			throw new AapartownerException(ValidationMessageEnum.RequestRequiredIdError.getM());
		}
		
		return apartownerService.delete(reqBody.getId());
	}
}
