package com.ah.apartowner.controllers;

import java.lang.reflect.InvocationTargetException;
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
import com.ah.apartowner.models.request.ApartownerReq;
import com.ah.apartowner.models.request.CommonReq;
import com.ah.apartowner.models.response.ApartownerRes;
import com.ah.apartowner.services.AapartownerService;
import com.ah.commonlib.JsonConverter;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("apartowner")
public class ApartownerController {

	private final AapartownerService apartownerService;
	
	public final static String TABLENAME = "apartowners";
	public final static String PKCOLUMNNAME = "apartowner_id";
	public final static String PKPROPERTY = TABLENAME + "." + PKCOLUMNNAME;

	/**
	 * ~/apartowner/regist<br>
	 * apartownerの登録リクエスト取得
	 * @param reqBody
	 * @return
	 */
	@PostMapping("regist")
	public ApartownerRes registApartowner(@RequestBody CommonReq reqBody) {
		if (Objects.nonNull(reqBody.getId())) {
			throw new AapartownerException("登録リクエストに主キーが指定されています。");
		}
		return apartownerService.regist(JsonConverter.deserializeJson(reqBody.getData(), ApartownerReq.class));
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
			throw new AapartownerException("更新リクエストに主キーが指定されていません。対象データをしぼれません。");
		}
		ApartownerReq reqData = JsonConverter.deserializeJson(reqBody.getData(), ApartownerReq.class);
		reqData.setApartownerId(reqBody.getId());
		return apartownerService.update(reqData);
	}

	/**
	 * ~/apartowner/update/parts<br>
	 * apartownerの部分項目更新リクエスト取得
	 * @param reqBody
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	@PutMapping("update/parts")
	public List<Map<String, Object>> updatePartsApartowner(@RequestBody List<Map<String, Object>> reqBody) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if(!reqBody.stream().allMatch(reqData->reqData.containsKey(PKPROPERTY))) {
			throw new AapartownerException("更新リクエストに主キーが指定されていません。対象データをしぼれません。");
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
			throw new AapartownerException("削除クエストに主キーが指定されていません。対象データをしぼれません。");
		}
		
		return apartownerService.delete(reqBody.getId());
	}
}
