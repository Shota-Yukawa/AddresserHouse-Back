package com.ah.residence.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ah.residence.exception.ResidenceException;
import com.ah.residence.models.req.CommonReq;
import com.ah.residence.models.res.ApartResidenceRes;
import com.ah.residence.services.ApartResidenceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("residence/apart")
@RequiredArgsConstructor
public class ApartResidenceController {

	private final ApartResidenceService apartResidenceService;

	/**
	 * 登録用リクエストマッピング
	 * 
	 * @param reqBody CommonReq
	 * @return addresser_residence_id(主キー)のみ
	 */
	@PostMapping("regist")
	public ApartResidenceRes addApartResidences(@RequestBody CommonReq reqBody) {
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
	public ApartResidenceRes updateApartResidence(@RequestBody CommonReq reqBody) throws ResidenceException {
		return apartResidenceService.update(reqBody);
	}

	/**
	 * 削除用リクエストマッピング
	 * 
	 * @param reqBody CommonReq
	 * @throws ResidenceException
	 */
	@DeleteMapping("delete")
	public void deleteApartResidence(@RequestBody CommonReq reqBody) throws ResidenceException {
		Integer targegtId = reqBody.getId();
		apartResidenceService.delete(targegtId);
	}
}
